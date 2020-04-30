package com.jh.restaurant.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author hjh
 * @version 1.0
 * @date 2020/4/28 16:54
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:/E:/IDEA/restaurant" +
                "/src/main/resources/static/upload/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

}
