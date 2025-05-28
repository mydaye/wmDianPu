package com.heycolor.wmdianpudemo;


import com.heycolor.wmdianpudemo.constant.ReturnInfo;
import com.heycolor.wmdianpudemo.myBean.*;
import com.heycolor.wmdianpudemo.service.ManageService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.heycolor.wmdianpudemo.constant.StateCode.*;

@RestController
@RequestMapping("/admin")
public class ManageApi {


    @Resource
    private ManageService manageService;


    // 登录
    // 固定账号密码 admin
    @PostMapping("/login")
    public ResponseEntity<ReturnInfo> login(@RequestBody LoginDTO loginDTO) {
        try {
            manageService.login(loginDTO);
            return ResponseEntity.ok()
                    .body(ReturnInfo.res(SUCCESS, "", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ReturnInfo.res(FAILED, e.getMessage(), null));
        }
    }

    // 添加管理员-管理员信息修改
    @PostMapping("/save")
    public ResponseEntity<ReturnInfo> saveAdmin(@RequestBody admin admin) {
        try {
            manageService.saveAdmin(admin);
            return ResponseEntity.ok()
                    .body(ReturnInfo.res(SUCCESS, "", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ReturnInfo.res(FAILED, e.getMessage(), null));
        }
    }

    // minifo修改参数
    @PostMapping("/minfo/save")
    public ResponseEntity<ReturnInfo> saveMinfo(@RequestBody minfo minfo) {
        try {
            manageService.saveMinfo(minfo);
            return ResponseEntity.ok()
                    .body(ReturnInfo.res(SUCCESS, "", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ReturnInfo.res(FAILED, e.getMessage(), null));
        }
    }

    // xlist 增删改查
    @PostMapping("/xlist/save")
    public ResponseEntity<ReturnInfo> saveXlist(@RequestBody xlist xlist) {
        try {
            manageService.saveXlist(xlist);
            return ResponseEntity.ok()
                    .body(ReturnInfo.res(SUCCESS, "", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ReturnInfo.res(FAILED, e.getMessage(), null));
        }
    }

    @GetMapping("/xlist/delete/{id}")
    public ResponseEntity<ReturnInfo> deleteXlist(@PathVariable int id) {
        
        try {
            manageService.deleteXlist(id);
            return ResponseEntity.ok()
                    .body(ReturnInfo.res(SUCCESS, "", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ReturnInfo.res(FAILED, e.getMessage(), null));
        }
    }

    @PostMapping("/xlist/list")
    public ResponseEntity<ReturnInfo> getXlist(@RequestBody ListPage listPage) {
        try {
            List<xlist> list = manageService.getXlist(listPage);
            return ResponseEntity.ok()
                    .body(ReturnInfo.res(SUCCESS, "", list));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ReturnInfo.res(FAILED, e.getMessage(), null));
        }

    }

    // ylist 增删改查
    @PostMapping("/ylist/save")
    public ResponseEntity<ReturnInfo> savYlist(@RequestBody ylist ylist) {
        try {
            manageService.saveYlist(ylist);
            return ResponseEntity.ok()
                    .body(ReturnInfo.res(SUCCESS, "", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ReturnInfo.res(FAILED, e.getMessage(), null));
        }
    }

    @GetMapping("/ylist/delete/{id}")
    public ResponseEntity<ReturnInfo> deleteYlist(@PathVariable int id) {

        try {
            manageService.deleteYlist(id);
            return ResponseEntity.ok()
                    .body(ReturnInfo.res(SUCCESS, "", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ReturnInfo.res(FAILED, e.getMessage(), null));
        }
    }

    @PostMapping("/ylist/list")
    public ResponseEntity<ReturnInfo> getYlist(@RequestBody ListPage listPage) {
        try {
            List<xlist> list = manageService.getYlist(listPage);
            return ResponseEntity.ok()
                    .body(ReturnInfo.res(SUCCESS, "", list));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ReturnInfo.res(FAILED, e.getMessage(), null));
        }

    }


}
