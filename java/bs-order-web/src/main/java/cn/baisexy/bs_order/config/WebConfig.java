//package cn.baisexy.bs_order.config;
//
//import cn.baisexy.bs_order.interceptor.LoginInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//    @Autowired
//    private LoginInterceptor loginInterceptor;
//
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/user/login", "/admin/login", "/food/upload",
//                        "/user/getcode",
//                        "/images/food/*",
//                        "/captchaImage");
//    }
//
//
//    /**
//     * 配置虚拟路径映射
//     *
//     * @param registry
//     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        //文件磁盘图片url 映射 ，一下位windows下的
//        //配置server虚拟路径，handler为前台访问的目录，locations为files相对应的本地路径
//        registry.addResourceHandler("/images/food/**").addResourceLocations("file:E:\\images\\"); // 轮转图片映射
//    }
//}