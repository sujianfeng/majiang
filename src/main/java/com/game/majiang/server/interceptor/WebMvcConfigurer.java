package com.game.majiang.server.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Administrator on 2016/8/28.
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new Interceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}