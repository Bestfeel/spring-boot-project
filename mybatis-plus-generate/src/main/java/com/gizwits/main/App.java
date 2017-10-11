package com.gizwits.main;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by feel on 2017/10/10.
 */
@SpringBootApplication(scanBasePackages = {"com.gizwits"})
public class App {
    public static void main(String[] args) throws Exception {


        SpringApplication.run(App.class, args);

    }

}
