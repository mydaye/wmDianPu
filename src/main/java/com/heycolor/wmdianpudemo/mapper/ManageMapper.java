package com.heycolor.wmdianpudemo.mapper;

import com.heycolor.wmdianpudemo.myBean.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    List<xlist> getXlist(ListPage listPage);

    List<xlist> getYlist(ListPage listPage);

    boolean checkXid(int xid);

    boolean checkXidOnYlist(int id);
}
