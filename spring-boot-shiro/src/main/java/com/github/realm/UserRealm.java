package com.github.realm;

import com.github.entity.User;
import com.github.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by feel on 2017/10/10.
 */
@Component
public class UserRealm extends AuthorizingRealm {


    @Autowired
    private IUserService iUserService;

    /**
     * 认证（用户登录）
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userCode = (String) token.getPrincipal();

        System.out.println("---token   -doGetAuthenticationInfo---");
        User user = iUserService.findByName(userCode);
        System.out.println("User--" + user);
        if (user == null) {
            throw new UnknownAccountException("账户不存在");
        }

        // 从获得密码
        String password = user.getPassword();

        if (password == null) {
            throw new IncorrectCredentialsException("密码错误！");
        }
        // 清除user对象中的密码
        user.setPassword("");


        // 如果查询到返回认证信息AuthenticationInfo
        // 将user设置simpleAuthenticationInfo
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, password.toCharArray(), getName());
        return simpleAuthenticationInfo;
    }

    /**
     * 权限认证
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        System.out.println("-- principals --doGetAuthorizationInfo  权限认证---");
        User user = (User) principals.getPrimaryPrincipal();

        if (user != null) {
            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

            info.addRole("admin");
            List<String> permissions = new ArrayList<>();
//            permissions.add("user:create");
//            permissions.add("user:add");
//            permissions.add("user:view");
            permissions.add("write");
            info.addStringPermissions(permissions);
            System.out.println("--user---" + user);
            return info;
        }

        // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
        return null;
    }



}