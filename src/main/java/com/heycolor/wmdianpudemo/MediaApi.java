package com.heycolor.wmdianpudemo;

import com.heycolor.wmdianpudemo.constant.ReturnInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import static com.heycolor.wmdianpudemo.constant.StateCode.FAILED;
import static com.heycolor.wmdianpudemo.constant.StateCode.SUCCESS;


@RestController
@RequestMapping("/media")
public class MediaApi {

    // 图片保存位置
    @Value("${imagePath}")
    private String imageUploadPath;

    // 视频保存位置
    @Value("${videoPath}")
    private String videoUploadPath;

    // 允许的文件类型
    private static final String[] IMAGE_TYPES = {"image/jpeg", "image/png", "image/gif"};
    private static final String[] VIDEO_TYPES = {"video/mp4", "video/quicktime", "video/avi"};

    // 上传图片
    @PostMapping("/image")
    public ResponseEntity<ReturnInfo> uploadImage(@RequestParam("file") MultipartFile file,@RequestParam("oldFile") String oldFile) {
        return handleFileUpload(file,oldFile, imageUploadPath, IMAGE_TYPES);
    }

    // 上传视频
    @PostMapping("/video")
    public ResponseEntity<ReturnInfo> uploadVideo(@RequestParam("file") MultipartFile file,@RequestParam("oldFile") String oldFile) {
        return handleFileUpload(file,oldFile, videoUploadPath, VIDEO_TYPES);
    }

    private ResponseEntity<ReturnInfo> handleFileUpload(MultipartFile file,String oldFile, String uploadPath, String[] allowedTypes) {
        try {
            // 检查文件是否为空
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body(ReturnInfo.res(FAILED, "文件不能为空", null));
            }

            // 验证文件类型
            if (!isValidFileType(file, allowedTypes)) {
                return ResponseEntity.badRequest().body(ReturnInfo.res(FAILED, "不支持的文件类型", null));
            }

            // 检查文件大小（示例限制为20MB）
            long MAX_FILE_SIZE = 20L * 1024 * 1024; // 20MB in bytes
            if (file.getSize() > MAX_FILE_SIZE) {
                return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                        .body(ReturnInfo.res(FAILED,
                                String.format("文件大小超过限制（最大允许 %.2fMB）",
                                        MAX_FILE_SIZE / (1024.0 * 1024.0)),
                                null));
            }

            // 创建上传目录（如果不存在）
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // 生成唯一文件名
            String originalFileName = file.getOriginalFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String uniqueFileName = createCode() + fileExtension;

            // 保存文件
            Path path = Paths.get(uploadPath + uniqueFileName);
            Files.write(path, file.getBytes());

            //检测是否有旧文件，有的话删除
            // 旧文件在同目录 文件名为oldFile
            // 删除旧文件（如果存在且合法）
            if (StringUtils.hasText(oldFile)) {
                try {
                    Path oldFilePath = Paths.get(uploadPath, oldFile);

                    // 安全验证：确保旧文件在指定目录内
                    if (isValidFilePath(oldFilePath, uploadPath)) {
                        if (Files.exists(oldFilePath)) {
                            Files.delete(oldFilePath);
//                            log.info("已删除旧文件: {}", oldFilePath);
                        } else {
//                            log.warn("旧文件不存在: {}", oldFilePath);
                        }
                    } else {
//                        log.warn("非法文件路径尝试: {}", oldFilePath);
                    }
                } catch (Exception e) {
                    // 删除失败不影响新文件上传，但记录日志
//                    log.error("删除旧文件失败: " + oldFile, e);
                }
            }


            // 返回文件访问路径
//            String fileAccessPath = "/uploads/" +
//                    (uploadPath.contains("images") ? "images/" : "videos/")
//                    + uniqueFileName;

            //返回文件名
            return ResponseEntity.ok(ReturnInfo.res(SUCCESS, "文件上传成功", uniqueFileName));

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(ReturnInfo.res(FAILED, "文件上传失败", null));
        }
    }

    private boolean isValidFileType(MultipartFile file, String[] allowedTypes) throws IOException {
        String fileContentType = file.getContentType();
        for (String type : allowedTypes) {
            if (type.equalsIgnoreCase(fileContentType)) {
                return true;
            }
        }
        return false;
    }

    // 辅助方法：验证文件路径是否在允许的目录内
    private boolean isValidFilePath(Path filePath, String basePath) {
        try {
            Path normalizedBase = Paths.get(basePath).normalize().toAbsolutePath();
            Path normalizedFile = filePath.normalize().toAbsolutePath();
            return normalizedFile.startsWith(normalizedBase);
        } catch (InvalidPathException e) {
            return false;
        }
    }

    private static final String CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    // 生成随机代码
    public static String createCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();

        // 生成随机的字符（大写字母和数字）
        for (int i = 0; i < 12; i++) {
            int index = random.nextInt(CHARSET.length());
            code.append(CHARSET.charAt(index));
        }

        return code.toString();
    }

}
