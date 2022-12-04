package cn.baisexy.bs_order.utils;

import cn.baisexy.bs_order.api.entity.Admin;
import cn.baisexy.bs_order.api.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class JwtUtil {

    // 过期时间： 一天
//    public static final long EXPIRE = 1000 * 60 * 60 * 24;

    public static final long EXPIRE = 1000 * 60 * 10;
    // 加密密钥
    public static final String APP_SECRET = "abcdefgqweasdzxcvzxcz";

    /**
     * 生成 token 字符串
     *
     * @param id
     * @param nickname
     * @return
     */
    public static String getJwtToken(String id, String nickname) {
        String jwtToken = Jwts.builder()
                // 设置 token 头部分
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                // 设置 token 的主题 subject，自定义
                .setSubject("token-demo")
                // 设置 token 的创建时间
                .setIssuedAt(new Date())
                // 设置过期时间，于何时过期
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                // 设置 token 的有效载荷
                .claim("id", id)
                .claim("nickname", nickname)
                // 设置签名，使用的加密算法以及密钥
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();

        return jwtToken;
    }


    public static String getJwtToken(User user) throws JsonProcessingException {
        if (user==null) return "";
        ObjectMapper ob = new ObjectMapper();
        String json = ob.writeValueAsString(user);
        String JwtToken = Jwts.builder().setHeaderParam("type", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject("lanqiao")
                .setIssuedAt(new Date())
                .claim("name", json)
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();
        return JwtToken;
    }

    public static String getJwtToken(Admin admin) throws JsonProcessingException {
        if (admin==null) return "";
        ObjectMapper ob = new ObjectMapper();
        String json = ob.writeValueAsString(admin);
        String JwtToken = Jwts.builder().setHeaderParam("type", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject("lanqiao")
                .setIssuedAt(new Date())
                .claim("admin", json)
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();
        return JwtToken;
    }


    public static String getJwtToken(String name) throws JsonProcessingException {
        if (name==null) return "";
        ObjectMapper ob = new ObjectMapper();
        String json = ob.writeValueAsString(name);
        String JwtToken = Jwts.builder().setHeaderParam("type", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject("lanqiao")
                .setIssuedAt(new Date())
                .claim("name", json)
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();
        return JwtToken;
    }


    /**
     * 判断token是否存在与有效
     *
     * @param jwtToken
     * @return
     */
    public static boolean checkToken(String jwtToken) {
        if (StringUtils.isEmpty(jwtToken)) {
            return false;
        }
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 判断token是否存在与有效
     *
     * @param request Http请求对象
     * @return 如果token有效返回true，否则返回false
     */
    public static boolean checkToken(HttpServletRequest request) {
        // 从http请求头中获取token字符串
        String jwtToken = request.getHeader("token");

        return checkToken(jwtToken);
    }


    /**
     * 根据token获取会员id，根据用户 id 查询数据库获取用户基本信息
     *
     * @return
     */
    public static String getMemberIdByJwtToken(String jwtToken) {
        if (StringUtils.isEmpty(jwtToken)) {
            return "";
        }
        Jws<Claims> claimsJws =
                Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (String) claims.get("id");
    }

    public static User getUserByJwtToken(String token) throws JsonProcessingException {
        if (StringUtils.isEmpty(token)) return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        String json = (String) claims.get("user");
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(json, User.class);
        return user;
    }

    public static String getNameByJwtToken(String token) throws JsonProcessingException {
        if (StringUtils.isEmpty(token)) return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        String json = (String) claims.get("name");
        ObjectMapper objectMapper = new ObjectMapper();
        String name = objectMapper.readValue(json,String.class);
        return name;
    }


     //获取名字
    public static String getNameByJwtToken(HttpServletRequest request) throws JsonProcessingException {
        String jwtToken = request.getHeader("token");
        if (StringUtils.isEmpty(jwtToken)) return null;
        return getNameByJwtToken(jwtToken);
    }

}