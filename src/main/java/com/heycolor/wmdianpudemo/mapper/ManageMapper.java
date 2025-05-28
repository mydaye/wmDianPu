package com.heycolor.wmdianpudemo.mapper;

import com.heycolor.wmdianpudemo.myBean.admin;
import com.heycolor.wmdianpudemo.myBean.minfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManageMapper {

    void addAdmin(admin admin);

    void updateAdmin(admin admin);

    void addMinfo(minfo minfo);

    void updateMinfo(minfo minfo);
}
