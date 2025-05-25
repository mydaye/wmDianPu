package com.heycolor.wmdianpudemo.service;

import com.heycolor.wmdianpudemo.mapper.infoMapper;
import com.heycolor.wmdianpudemo.myBean.minfo;
import com.heycolor.wmdianpudemo.myBean.xlist;
import com.heycolor.wmdianpudemo.myBean.ylist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class infoService {
    private final infoMapper infomapper;

    @Autowired
    public infoService(infoMapper infomapper) {
        this.infomapper = infomapper;
    }

    public List<minfo> selectInfo() {
        return this.infomapper.selectInfo();
    }

    public List<xlist> selectXlist() {
        return this.infomapper.selectXlist();
    }

    public List<ylist> selectYlist() {
        return this.infomapper.selectYlist();
    }


}
