package com.hhf.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;

@ServletComponentScan(basePackages = "com.hhf.admin")
@SpringBootApplication
public class Boot04AdminApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Boot04AdminApplication.class, args);
//        context.getBean()
    }

}
