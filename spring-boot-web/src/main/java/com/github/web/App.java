package com.github.web;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author guojie.yf
 * @date 2018/7/6
 * @email guojie.yf@alibaba-inc.com
 */
@SpringBootApplication(scanBasePackages = {"com.github"})
@EnableSwagger2Doc
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
