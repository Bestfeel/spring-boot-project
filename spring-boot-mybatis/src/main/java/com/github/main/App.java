package com.github.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author guojie.yf
 * @date 2018/7/19
 * @email guojie.yf@alibaba-inc.com
 */
@SpringBootApplication(scanBasePackages = {"com.github"})
@EnableTransactionManagement
@MapperScan("com.github.mapper")
public class App {
    public static void main(String[] args) {

        SpringApplication.run(App.class, args);
    }
}
