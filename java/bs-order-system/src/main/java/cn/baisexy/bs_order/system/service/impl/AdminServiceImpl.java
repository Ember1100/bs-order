package cn.baisexy.bs_order.system.service.impl;

import cn.baisexy.bs_order.api.entity.Admin;
import cn.baisexy.bs_order.system.mapper.AdminMapper;
import cn.baisexy.bs_order.system.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin login(String aName, String aPwd) {
        return adminMapper.selectByUsernameAndPassword(aName,aPwd);
    }
}
