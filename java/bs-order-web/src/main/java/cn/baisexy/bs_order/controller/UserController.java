package cn.baisexy.bs_order.controller;


import cn.baisexy.bs_order.api.entity.User;
import cn.baisexy.bs_order.api.pojo.send.DataReturn;
import cn.baisexy.bs_order.api.pojo.send.ReturnCode;
import cn.baisexy.bs_order.api.pojo.send.TableReturn;
import cn.baisexy.bs_order.system.service.UserService;
import cn.baisexy.bs_order.utils.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil ;

    @Value("${redis.keys.login-tokens}")
    private String login_token;

    @Value("${redis.keys.code_uuid}")
    private String uuid;

    //验证码
    private String captcha;

    @PostMapping("/login")
    public ResultUtil login(@RequestBody User user) throws JsonProcessingException {
        System.out.println(user);
         if (!redisUtil.get(captcha).equals(user.getCode())){
             return new ResultUtil(ReturnCode.FAIL);
         }
        int i = userService.login(user.getUName(),user.getUPwd());
        if (i==1) {
            User u = userService.getUser(user.getUName());
            String id = UUID.randomUUID().toString();
            System.out.println("生成的用户 id 为：" + id);
            String token = JwtUtil.getJwtToken(u.getUName());//生成token
            //保存到redis
            String key = login_token+u.getUName();
            redisUtil.set(key,token,600);
            return new ResultUtil(new ArrayList(){{add(u);}}, ReturnCode.SUCCESS,token);
        }
        return new ResultUtil(ReturnCode.FAIL);
    }


    /**
     * 生成验证码的接口
     *
     * @param response Response对象
     * @param request  Request对象
     * @throws Exception
     */
    @RequestMapping("/getcode")
    public void getCode(HttpServletResponse response, HttpServletRequest request) throws Exception {
        // 获取到session
        HttpSession session = request.getSession();
        // 取到sessionid
        captcha =uuid+ session.getId();

        // 利用图片工具生成图片
        // 返回的数组第一个参数是生成的验证码，第二个参数是生成的图片
        Object[] objs = VerifyUtil.newBuilder()
                .setWidth(120)   //设置图片的宽度
                .setHeight(45)   //设置图片的高度
                .setSize(4)      //设置字符的个数
                .setLines(10)    //设置干扰线的条数
                .setFontSize(25) //设置字体的大小
                .setTilt(true)   //设置是否需要倾斜
                .setBackgroundColor(Color.LIGHT_GRAY) //设置验证码的背景颜色
                .build()         //构建VerifyUtil项目
                .createImage();  //生成图片
        // 将验证码存入Session
        session.setAttribute("SESSION_VERIFY_CODE_" + captcha, objs[0]);
        // 打印验证码
        System.out.println(objs[0]);

        // 在redis中保存一个验证码最多尝试次数
        redisUtil.set(captcha,objs[0],90);

        // 将图片输出给浏览器
        BufferedImage image = (BufferedImage) objs[1];
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }


    @PostMapping("/getUserList")
    public TableReturn getUsers(@RequestBody TableReturn tableReturn) {

        if (" ".equals(tableReturn.getQuery()) ) {
            List<User> listUser = userService.getListUser(tableReturn.getTotal(), tableReturn.getPageNum());
            tableReturn.setData(listUser);
            System.out.println(tableReturn);
            return tableReturn;
        }
        List<User> list = userService.getListUser(tableReturn.getTotal(), tableReturn.getPageNum());
        tableReturn.setData(list);
        System.out.println(list);
        return tableReturn;
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public DataReturn update(@RequestBody User user) {
        int i;
        if (user.getUId()==null) {
            i=userService.save(user);
        } else {
            i=userService.save(user);
        }

        if (i==1) {
            return new DataReturn(ReturnCode.SUCCESS);
        }else {
            return new DataReturn(ReturnCode.FAIL);
        }
    }



    //名字用户
    @PostMapping("/getUser")
    public ResultUtil getUserList(@RequestBody TableReturn tableReturn) {
        System.out.println(tableReturn.getQuery());
        List<User> users = userService.searchUser(tableReturn.getQuery());
        System.out.println(users);
        if (users!=null)  {
            return new ResultUtil(users,ReturnCode.SUCCESS);
        }
       return new ResultUtil(users,ReturnCode.FAIL);
    }


    @PostMapping("/delUser")
    public ResultUtil getUserList(Integer uid) {
         int i = userService.delUser(uid);
         if (i==1)  return new ResultUtil(ReturnCode.SUCCESS);
        return new ResultUtil(ReturnCode.FAIL);
    }


}
