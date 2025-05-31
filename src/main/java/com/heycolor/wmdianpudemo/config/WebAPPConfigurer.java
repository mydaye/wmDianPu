package com.heycolor.wmdianpudemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Author: LunKeee
 * date: 2023/7/24 13:39
 * Description:web项目配置类
 */

@Configuration
public class WebAPPConfigurer implements WebMvcConfigurer {


    //图片
    @Value("${imagePath}")
    private String imagePath;

    //视频
    @Value("${videoPath}")
    private String videoPath;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns(
                        "http://localhost:[*]",          // 开发环境（所有端口）
                        "http://127.0.0.1:[*]",         // 本地IP访问
                        "http://47.121.128.9:9090"          // 生产HTTP
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders("Authorization", "Content-Disposition")
                .allowCredentials(true)
                .maxAge(3600);

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resource/image/**").addResourceLocations("file:" + imagePath);
        registry.addResourceHandler("/resource/video/**").addResourceLocations("file:" + videoPath);
        }

//    @Bean
//    public SysInterceptor sysInterceptor(){
//        return new SysInterceptor();
//    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 白名单 "/user/up"要去掉
//        String[] patterns=new String[]{"/user/signup","/user/login","/user/up","/error"};
        // 鉴权拦截器，访问需要token的页面需要
//        registry.addInterceptor(sysInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns(patterns)
//                .order(1);
    }
}
