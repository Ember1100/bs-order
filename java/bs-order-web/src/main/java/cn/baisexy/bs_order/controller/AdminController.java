package cn.baisexy.bs_order.controller;

import cn.baisexy.bs_order.api.entity.Admin;
import cn.baisexy.bs_order.api.pojo.send.ReturnCode;
import cn.baisexy.bs_order.system.service.AdminService;
import cn.baisexy.bs_order.utils.JwtUtil;
import cn.baisexy.bs_order.utils.RedisUtil;
import cn.baisexy.bs_order.utils.ResultUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RedisUtil redisUtil ;

    @Value("${redis.keys.login-tokens}")
    private String login_token;

    @PostMapping("/login")
    public ResultUtil login(@RequestBody Admin admin) throws JsonProcessingException {
        System.out.println(admin);
            Admin admin2 = adminService.login(admin.getaName(), admin.getaPwd());
            if (admin == null) {
                return new ResultUtil<>(ReturnCode.FAIL);
            } else {
                String jwtToken = JwtUtil.getJwtToken(admin2.getaName());
                String key = login_token+admin2.getaName();
                redisUtil.set(key,jwtToken,600);
                System.out.println(jwtToken);
                return new ResultUtil(new ArrayList(){{add(admin2);}}, ReturnCode.SUCCESS,jwtToken);
            }
        }



}
