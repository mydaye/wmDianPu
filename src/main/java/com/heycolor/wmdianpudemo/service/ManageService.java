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
        if (loginDTO.getUserName() == null || loginDTO.getUserPsw() == null)
            throw new BuzException("账号和密码不能为空");

        // 验证账号密码
        if (!"admin".equals(loginDTO.getUserName()) || !"admin".equals(loginDTO.getUserPsw()))
            throw new BuzException("账号或密码错误");
    }

    public void saveAdmin(admin admin) {
        // 参数不能为空
        if (admin.getApptype() == null || admin.getCode() == null)
            throw new BuzException("参数不能为空");

        if (admin.getId() == -1) {
            // 新增
            try {
                manageMapper.addAdmin(admin);
            } catch (Exception e) {
                throw new BuzException("新增失败");
            }
        } else {
            //修改
            try {
                manageMapper.updateAdmin(admin);
            } catch (Exception e) {
                throw new BuzException("修改失败");
            }
        }

    }

    public void saveMinfo(minfo minfo) {
        // 参数不能为空
//        if (minfo.isNull())
//            throw new BuzException("参数不能为空");

        if (minfo.getId() == -1) {
            // 新增
            try {
                manageMapper.addMinfo(minfo);
            } catch (Exception e) {
                throw new BuzException("新增失败");
            }
        } else {
            //修改
            try {
                manageMapper.updateMinfo(minfo);
            } catch (Exception e) {
                throw new BuzException("修改失败");
            }
        }


    }

    public void saveXlist(xlist xlist) {
        // 参数不能为空
        if (xlist.isNull())
            throw new BuzException("参数不能为空");
        if (xlist.getId() == -1) {
            // 新增
            try {
                manageMapper.addXlist(xlist);
            } catch (Exception e) {
                throw new BuzException("新增失败");
            }
        } else {
            //修改
            try {
                manageMapper.updateXlist(xlist);
            } catch (Exception e) {
                throw new BuzException("修改失败");
            }
        }

    }

    public void deleteXlist(int id) {
        try {
            // 检测ylist 是否还有改xid存在
            if (manageMapper.checkXidOnYlist(id))
                throw new BuzException("类目下还有商品");
            manageMapper.deleteXlist(id);
        } catch (BuzException e) {
            throw e; // 业务异常直接抛出
        } catch (Exception e) {
            throw new BuzException("删除失败");
        }
    }

    public List<xlist> getXlist(ListPage listPage) {
        // 参数不能为空
        if (listPage.getPageIndex() == 0 || listPage.getPageSize() == 0)
            throw new BuzException("参数不能为空");

        listPage.setPageStart((listPage.getPageIndex() - 1) * listPage.getPageSize());

        return manageMapper.getXlist(listPage);

    }

    public void saveYlist(ylist ylist) {
        // 参数不能为空
        if (ylist.isNull())
            throw new BuzException("参数不能为空");
        if (ylist.getId() == -1) {
            // 新增操作
            try {
                // 参数校验
                if (ylist.getXid() == 0 || ylist.getXid() <= 0) {
                    throw new BuzException("xid不能为空且必须大于0");
                }

                // 检测ylist中的xid是否存在
                if (!manageMapper.checkXid(ylist.getXid())) {
                    throw new BuzException("类目不存在");
                }
                manageMapper.addYlist(ylist);

            } catch (BuzException e) {
                throw e; // 业务异常直接抛出
            } catch (Exception e) {
//                log.error("新增ylist异常", e);
                throw new BuzException("系统异常，新增失败");
            }
        } else {
            //修改
            try {
                // 检测 ylist中的xid是否存在
                if (manageMapper.checkXid(ylist.getXid()))
                    throw new BuzException("类目不存在");
                manageMapper.updateYlist(ylist);
            } catch (Exception e) {
                throw new BuzException("修改失败");
            }
        }
    }

    public void deleteYlist(int id) {
        try {
            manageMapper.deleteYlist(id);
        } catch (Exception e) {
            throw new BuzException("删除失败");
        }
    }

    public List<ylist> getYlist(ListPage listPage) {
        // 参数不能为空
        if (listPage.getPageIndex() == 0 || listPage.getPageSize() == 0)
            throw new BuzException("参数不能为空");

        listPage.setPageStart((listPage.getPageIndex() - 1) * listPage.getPageSize());

        return manageMapper.getYlist(listPage);
    }

    public List<admin> getAdminList(ListPage listPage) {
        // 参数不能为空
        if (listPage.getPageIndex() == 0 || listPage.getPageSize() == 0)
            throw new BuzException("参数不能为空");

        listPage.setPageStart((listPage.getPageIndex() - 1) * listPage.getPageSize());

        return manageMapper.getAdminList(listPage);
    }

    public int getXlistTotal() {
        return manageMapper.getXlistTotal();
    }

    public int getYlistTotal() {
        return manageMapper.getYlistTotal();
    }

    public int getAdminTotal() {
        return manageMapper.getAdminTotal();
    }

    public minfo getMinfo() {
        return manageMapper.getMinfo();
    }
}
