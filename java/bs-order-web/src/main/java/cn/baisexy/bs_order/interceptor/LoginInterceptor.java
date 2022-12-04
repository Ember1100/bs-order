package cn.baisexy.bs_order.interceptor;



import cn.baisexy.bs_order.api.entity.User;
import cn.baisexy.bs_order.api.pojo.send.ReturnCode;
import cn.baisexy.bs_order.utils.JwtUtil;
import cn.baisexy.bs_order.utils.RedisUtil;
import cn.baisexy.bs_order.utils.ResultUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
   private RedisUtil redisUtil;

    @Value("${redis.keys.login-tokens}")
    private String loginToken;

    @Value("${redis.expire-time.token-expire_time}0")
    private Long expireTime;

    //方式执行之前进行拦截： true：放行； false：拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(request.getHeader("token"));
        System.out.println("==经过了拦截器===");
        response.setContentType("text/html;charset=utf8");
        if (!JwtUtil.checkToken(request)) {
            //给前端返回的Result对象
            ResultUtil resultUtil = new ResultUtil(ReturnCode.TOKENNOTLEGAL);
            // 把 result 对象转变为 json 字符串
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(resultUtil);
            //把json字符串发给前端
            response.getWriter().print(json);
            return false;
        }else {
            //token 合法，合法的token，还要检查是否过期
            String token = request.getHeader("token") ;
            //获取用户名
            String name = JwtUtil.getNameByJwtToken(request);
            System.out.println(name);
            String key = loginToken + name;
            //判断时间
            if (redisUtil.hasKey(key)) {
                //存在
                //没有过期的话，检查token是不是快过期了，如果快过期了，那么续期
                if (redisUtil.getExpire(key)<60) {
                    //续期
                    redisUtil.del(key);
                    redisUtil.set(key,token,expireTime);
                }
            }else {
               //不存在，表示已经过期
               ResultUtil res= new ResultUtil(ReturnCode.TOKENEXPIRED);
               //把result 对象转变为json字符串
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(res);
                //把json字符串发给前端
                response.getWriter().print(json);
                return false;
            }
        }
        return  true ;
    }
}
