package com.github.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
@Slf4j
public class LoginController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, @ModelAttribute("shiroLoginFailure") String a, @RequestParam("username") String username) {

        log.info("账号" + username + "登录失败");
        // 如果登陆失败从request中获取认证异常信息，shiroLoginFailure就是shiro异常类的全限定名
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
        log.info(exceptionClassName);

        String message = "登录失败！";
        if (exceptionClassName != null) {
            if (UnknownAccountException.class.getName().equals(
                    exceptionClassName)) {
                message = "用户名/密码错误";
                log.error("账号不存在----" + username);
            } else if (IncorrectCredentialsException.class.getName().equals(
                    exceptionClassName)) {
                message = "用户名/密码错误";
                log.error("用户名/密码错误----" + username);
            } else if (ExcessiveAttemptsException.class.getName().equals(
                    exceptionClassName)) {
                message = "登录失败次数过多";
                log.error("登录失败次数过多----" + username);
            } else if (LockedAccountException.class.getName().equals(
                    exceptionClassName)) {
                message = "账号已被锁定";
                log.error("账号已被锁定----" + username);
            } else if (DisabledAccountException.class.getName().equals(
                    exceptionClassName)) {
                message = "账号已被禁用";
                log.error("账号已被禁用----" + username);
            } else if (ExpiredCredentialsException.class.getName().equals(
                    exceptionClassName)) {
                message = "账号已过期";
                log.error("账号已过期----" + username);
            } else if (UnauthorizedException.class.getName().equals(
                    exceptionClassName)) {
                message = "账号没有相应的授权";
                log.error("账号没有相应的授权----" + username);
            } else {
                message = "未知异常，请重试";
                log.error("未知错误-----" + username);
            }
        }

        request.setAttribute("message", message);
        request.setAttribute("username", username);

        return "login";
    }
}