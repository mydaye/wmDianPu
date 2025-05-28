package com.heycolor.wmdianpudemo.service;


import com.heycolor.wmdianpudemo.constant.BuzException;
import com.heycolor.wmdianpudemo.myBean.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageService {

    public void login(LoginDTO loginDTO) {
        // 参数不能为空
        if(loginDTO.getUserName() == null || loginDTO.getUserPsw() == null)
            throw new BuzException("账号和密码不能为空");

        // 验证账号密码
        if(!"admin".equals(loginDTO.getUserName()) || !"admin".equals(loginDTO.getUserPsw()))
            throw new BuzException("账号或密码错误");
    }

    public void saveAdmin(admin admin) {
    }

    public void saveMinfo(minfo minfo) {
    }

    public void saveXlist(xlist xlist) {
    }

    public void deleteXlist(int id) {
    }

    public List<xlist> getXlist(ListPage listPage) {
        return null;
    }

    public void saveYlist(ylist ylist) {
    }

    public void deleteYlist(int id) {
    }

    public List<xlist> getYlist(ListPage listPage) {
        return null;
    }
}
