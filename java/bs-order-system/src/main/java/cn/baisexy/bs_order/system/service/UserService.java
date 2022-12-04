package cn.baisexy.bs_order.system.service;


import cn.baisexy.bs_order.api.entity.User;

import java.util.List;

public interface UserService {

    int login(String uName,String uPwd);

    User getUser(String uName);

    User getUserById(Integer uId);

    List<User> getAll();


    void updatePwd(String uPwd, Integer uId);


    List<User> getListUser(int total, int pageNum);

    List<User> searchUser(String s);

    int delUser(Integer id);

    User findById(Integer uId);

    int addUser(User user);

    int save(User user);

}
