package cn.baisexy.bs_order.system.service.impl;

import cn.baisexy.bs_order.api.entity.User;
import cn.baisexy.bs_order.system.mapper.UserMapper;
import cn.baisexy.bs_order.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public int login(String uName,String uPwd) {
        User u = userMapper.findUser(uName);
        if (u!=null) {
            if (u.getUPwd().equals(uPwd)){
                return  1;
            } else {
                return 0;
            }
        }
        return -1;
    }

    @Override
    public User getUser(String uname) {
        return userMapper.findUser(uname);
    }

    @Override
    public User getUserById(Integer uId) {
        return userMapper.selectByPrimaryKey(uId);
    }

    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }


    @Override
    public User findById(Integer uId) {
        return userMapper.selectByPrimaryKey(uId);
    }

    @Override
    public int addUser(User user) {
          return userMapper.insert(user);
    }



    @Override
    public void updatePwd(String uPwd, Integer uId) {
        userMapper.updatePwd(uPwd, uId);
    }


    public List<User> getListUser(int total, int pageNum) {
        return userMapper.getListUser(total * (pageNum - 1), total);
    }

    @Override
    public List<User> searchUser(String uName) {
        return userMapper.searchByName(uName);
    }

    @Override
    public int  delUser(Integer id) {
       return  userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int save(User user) {
        if (user.getUId()==null) {
            return userMapper.insert(user);
        }else {
            return userMapper.updateByPrimaryKeySelective(user);
        }
    }
}
