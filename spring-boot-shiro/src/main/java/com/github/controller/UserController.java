package com.github.controller;


import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author feel
 * @since 2017-10-10
 */
@Controller
public class UserController {
    @SuppressWarnings("Duplicates")
    @RequestMapping("/")
    public String home(HttpServletRequest request, Model model) {

        String name = "World";
        System.out.println("----- home---");
        Subject subject = SecurityUtils.getSubject();

        PrincipalCollection principalCollection = subject.getPrincipals();

        if (principalCollection != null && !principalCollection.isEmpty()) {
            Collection<Map> principalMaps = subject.getPrincipals().byType(Map.class);
            if (CollectionUtils.isEmpty(principalMaps)) {
                name = subject.getPrincipal().toString();
            } else {
                name = (String) principalMaps.iterator().next().get("username");
            }
        }

        model.addAttribute("name", name);

        return "hello";
    }


    @ResponseBody
    @RequestMapping("unauthorized")
    public String error() {
        return "没有权限";
    }
}
