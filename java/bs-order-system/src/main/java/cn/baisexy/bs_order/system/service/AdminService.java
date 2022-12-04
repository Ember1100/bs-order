package cn.baisexy.bs_order.system.service;

import cn.baisexy.bs_order.api.entity.Admin;

public interface AdminService {

    public Admin login(String aName, String aPwd);
}
