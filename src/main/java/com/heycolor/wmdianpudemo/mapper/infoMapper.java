package com.heycolor.wmdianpudemo.mapper;

import com.heycolor.wmdianpudemo.myBean.admin;
import com.heycolor.wmdianpudemo.myBean.minfo;
import com.heycolor.wmdianpudemo.myBean.xlist;
import com.heycolor.wmdianpudemo.myBean.ylist;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface infoMapper {
    List<admin> userLogin(String apptype, String code);
    List<minfo> selectInfo();
    List<xlist> selectXlist();
    List<ylist> selectYlist();
}
