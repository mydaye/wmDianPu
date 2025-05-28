package com.heycolor.wmdianpudemo.mapper;

import com.heycolor.wmdianpudemo.myBean.admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManageMapper {

    void addAdmin(admin admin);

    void updateAdmin(admin admin);
}
