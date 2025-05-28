package com.heycolor.wmdianpudemo.service;


import com.heycolor.wmdianpudemo.constant.BuzException;
import com.heycolor.wmdianpudemo.mapper.ManageMapper;
import com.heycolor.wmdianpudemo.myBean.*;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageService {

    @Resource
    private ManageMapper manageMapper;

    public void login(LoginDTO loginDTO) {
        // 参数不能为空
        if(loginDTO.getUserName() == null || loginDTO.getUserPsw() == null)
            throw new BuzException("账号和密码不能为空");

        // 验证账号密码
        if(!"admin".equals(loginDTO.getUserName()) || !"admin".equals(loginDTO.getUserPsw()))
            throw new BuzException("账号或密码错误");
    }

    public void saveAdmin(admin admin) {
        // 参数不能为空
        if(admin.getApptype() == null || admin.getCode() == null)
            throw new BuzException("参数不能为空");

        if(admin.getId() == -1){
            // 新增
            try {
                manageMapper.addAdmin(admin);
            }catch (Exception e){
                throw new BuzException("新增失败");
            }
        }else{
            //修改
            try{
                manageMapper.updateAdmin(admin);
            }catch (Exception e){
                throw new BuzException("修改失败");
            }
        }

    }

    public void saveMinfo(minfo minfo) {
        // 参数不能为空
        if(minfo.isNull())
            throw new BuzException("参数不能为空");

        if(minfo.getId() == -1){
            // 新增
            try {
                manageMapper.addMinfo(minfo);
            }catch (Exception e){
                throw new BuzException("新增失败");
            }
        }else{
            //修改
            try{
                manageMapper.updateMinfo(minfo);
            }catch (Exception e){
                throw new BuzException("修改失败");
            }
        }


    }

    public void saveXlist(xlist xlist) {
        // 参数不能为空
        if(xlist.isNull())
            throw new BuzException("参数不能为空");
        if(xlist.getId() == -1){
            // 新增
            try {
                manageMapper.addXlist(xlist);
            }catch (Exception e){
                throw new BuzException("新增失败");
            }
        }else{
            //修改
            try{
                manageMapper.updateXlist(xlist);
            }catch (Exception e){
                throw new BuzException("修改失败");
            }
        }

    }

    public void deleteXlist(int id) {
        try{
            manageMapper.deleteXlist(id);
        }catch (Exception e){
            throw new BuzException("删除失败");
        }
    }

    public List<xlist> getXlist(ListPage listPage) {
        return null;
    }

    public void saveYlist(ylist ylist) {
        // 参数不能为空
        if(ylist.isNull())
            throw new BuzException("参数不能为空");
        if(ylist.getId() == -1){
            // 新增
            try {
                manageMapper.addYlist(ylist);
            }catch (Exception e){
                throw new BuzException("新增失败");
            }
        }else{
            //修改
            try{
                manageMapper.updateYlist(ylist);
            }catch (Exception e){
                throw new BuzException("修改失败");
            }
        }
    }

    public void deleteYlist(int id) {
        try{
            manageMapper.deleteYlist(id);
        }catch (Exception e){
            throw new BuzException("删除失败");
        }
    }

    public List<xlist> getYlist(ListPage listPage) {
        return null;
    }
}
