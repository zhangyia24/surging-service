package com.surging.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by zhangdongmao on 2019/1/13.
 */
@Configuration
public class MVCConfig implements WebMvcConfigurer {
        //配置拦截器
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        List<String> excludePatterns = new ArrayList<String>();
//        excludePatterns.add("/surgingAdmin/index");
//        excludePatterns.add("/surgingAdmin/login");
//        excludePatterns.add("/lib/**");
//        excludePatterns.add("/public/**");
//        excludePatterns.add("/static/**");
//        excludePatterns.add("/temp/**");
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns(excludePatterns);
//
//    }
    //配置跨域
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
//        .allowedOrigins("http://192.168.1.97")
//        .allowedMethods("GET", "POST")
//        .allowCredentials(false).maxAge(3600);
    }
}
