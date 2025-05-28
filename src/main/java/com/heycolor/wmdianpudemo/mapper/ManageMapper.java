package com.heycolor.wmdianpudemo.mapper;

import com.heycolor.wmdianpudemo.myBean.admin;
import com.heycolor.wmdianpudemo.myBean.minfo;
import com.heycolor.wmdianpudemo.myBean.xlist;
import com.heycolor.wmdianpudemo.myBean.ylist;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManageMapper {

    void addAdmin(admin admin);

    void updateAdmin(admin admin);

    void addMinfo(minfo minfo);

    void updateMinfo(minfo minfo);

    void addXlist(xlist xlist);

    void updateXlist(xlist xlist);

    void deleteXlist(int id);

    void deleteYlist(int id);

    void addYlist(ylist ylist);

    void updateYlist(ylist ylist);
}
