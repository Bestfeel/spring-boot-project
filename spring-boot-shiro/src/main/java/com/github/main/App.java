package com.github.main;


import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by feel on 2017/10/10.
 */
@SpringBootApplication(scanBasePackages = {"com.github"})
@ControllerAdvice
@Slf4j
public class App {

    /**
     * 用于处理编码问题
     *
     * @return
     */
    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

    /**
     * 配置springmvc异常处理器
     * 处理 未授权（AuthorizationException）页面
     *
     * @param e
     * @param model
     * @return
     */
    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleException(AuthorizationException e, Model model) {

        // you could return a 404 here instead (this is how github handles 403, so the user does NOT know there is a
        // resource at that location)
        log.debug("AuthorizationException was thrown", e);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", HttpStatus.FORBIDDEN.value());
        map.put("message", "No message available403");
        model.addAttribute("errors", map);

        return "error";
    }

    /**
     * 为所有的request添加用户主体
     *
     * @return
     */
    @ModelAttribute(name = "subject")
    public Subject subject() {
        return SecurityUtils.getSubject();
    }

    public static void main(String[] args) throws Exception {

        SpringApplication.run(App.class, args);

    }

}
