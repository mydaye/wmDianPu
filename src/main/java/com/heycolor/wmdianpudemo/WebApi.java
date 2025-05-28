package com.heycolor.wmdianpudemo;

import com.heycolor.wmdianpudemo.constant.ReturnInfo;
import com.heycolor.wmdianpudemo.myBean.*;
import com.heycolor.wmdianpudemo.service.infoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.heycolor.wmdianpudemo.constant.StateCode.*;

@RestController
public class WebApi {
    private final infoService mInfo;

    @Autowired
    public WebApi(infoService mInfo) {
        this.mInfo = mInfo;
    }


    @PostMapping({"/userLogin"})
    private ResponseEntity<ReturnInfo> userToLogin(@Validated @RequestBody userLogin bao) {
        List<admin> mData = this.mInfo.userByLogin(bao.getApptype(),bao.getCode());
        if (mData.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(ReturnInfo.res(Sql_Null_error, "用户不存在或密码错误", null));
        } else {
            return ResponseEntity.ok()
                    .body(ReturnInfo.res(SUCCESS, "", null));
        }
    }

    @GetMapping({"/getMainInfo"})
    private ResponseEntity<ReturnInfo> getMainInfo() {
        List<minfo> mData = this.mInfo.selectInfo();
        if (!mData.isEmpty()) {
            minfo b = mData.getFirst();

            return ResponseEntity.ok()
                    .body(ReturnInfo.res(SUCCESS, "", b));
        } else {
            return ResponseEntity.badRequest()
                    .body(ReturnInfo.res(FAILED, "没有数据", null));
        }
    }

    @GetMapping({"/getXlist"})
    private ResponseEntity<ReturnInfo> getXlist() {
        List<xlist> mData = this.mInfo.selectXlist();
        if (!mData.isEmpty()) {
            return ResponseEntity.ok()
                    .body(ReturnInfo.res(SUCCESS, "", mData));
        } else {
            return ResponseEntity.badRequest()
                    .body(ReturnInfo.res(FAILED, "没有数据", null));
        }
    }

    @GetMapping({"/getYlist"})
    private ResponseEntity<ReturnInfo> getYlist() {
        List<ylist> mData = this.mInfo.selectYlist();
        if (!mData.isEmpty()) {
            return ResponseEntity.ok()
                    .body(ReturnInfo.res(SUCCESS, "", mData));
        } else {
            return ResponseEntity.badRequest()
                    .body(ReturnInfo.res(FAILED, "没有数据", null));
        }
    }
}
