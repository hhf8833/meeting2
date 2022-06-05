package com.hhf.seckilldemo.config;


import com.hhf.seckilldemo.inteceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration

public class WebConfig implements WebMvcConfigurer {
 /*   @Autowired
    private UserArgumentResolver userArgumentResolver;*/
    @Autowired
    private UserInterceptor userInterceptor;

/*    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userArgumentResolver);
    }*/


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
/*        registry.addInterceptor(userInterceptor)
                .addPathPatterns("/**") //会拦截所有的请求，包括静态资源
                .excludePathPatterns("/","/login/**","/bootstrap/**","/jquery-validation/**","/css/**","/layers/**","/img/**","/js/**");*/
        registry.addInterceptor(userInterceptor)
                .addPathPatterns("/goods/toList")
                .excludePathPatterns("/bootstrap/**","/jquery-validation/**","/css/**","/layers/**","/img/**","/js/**");
    }
}
