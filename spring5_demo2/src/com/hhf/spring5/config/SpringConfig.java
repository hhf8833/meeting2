package com.hhf.spring5.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author HP
 * @EnableAspectJAutoProxy(proxyTargetClass = true)全注解开发，aop切片的时候用注解配置增强类
 * @ComponentScan(basePackages = {"com.hhf.spring5"}) 开启自动扫描生成bean类
 */
@Configuration //作为配置类，替代xml文件
@ComponentScan(basePackages = {"com.hhf.spring5"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringConfig {
}
