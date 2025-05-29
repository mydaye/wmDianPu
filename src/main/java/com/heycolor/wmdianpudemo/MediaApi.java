package com.heycolor.wmdianpudemo;

import com.heycolor.wmdianpudemo.constant.ReturnInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
    public ResponseEntity<ReturnInfo> uploadImage(@RequestParam("file") MultipartFile file) {
        return handleFileUpload(file, imageUploadPath, IMAGE_TYPES);
    }

    // 上传视频
    @PostMapping("/video")
    public ResponseEntity<ReturnInfo> uploadVideo(@RequestParam("file") MultipartFile file) {
        return handleFileUpload(file, videoUploadPath, VIDEO_TYPES);
    }

    private ResponseEntity<ReturnInfo> handleFileUpload(MultipartFile file, String uploadPath, String[] allowedTypes) {
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
