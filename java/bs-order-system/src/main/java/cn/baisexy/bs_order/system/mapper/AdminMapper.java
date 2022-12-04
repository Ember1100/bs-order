package cn.baisexy.bs_order.system.mapper;

import cn.baisexy.bs_order.api.entity.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer aId);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer aId);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

     Admin selectByUsernameAndPassword(@Param("aName") String aName, @Param("aPwd") String aPwd);
}