package cn.baisexy.bs_order.system.mapper;

import cn.baisexy.bs_order.api.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface UserMapper {
    int deleteByPrimaryKey(Integer uId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer uId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User findUser(String uName);

    List<User> getAll();

    void updatePwd(@Param("uPwd") String uPwd, @Param("uId") Integer uId);

    @Update("update user set u_id = #{uId}, u_pwd = #{uPwd}, u_address = #{uAddress}, u_pn = #{uPhone}, u_name = #{uName}, u_email = #{uEmail} where u_id = #{uId}")
    void updateInfo(User user);

    List<User> getListUser(@Param("begin") int begin, @Param("end") int end);


    List<User> searchByName(String s);


}