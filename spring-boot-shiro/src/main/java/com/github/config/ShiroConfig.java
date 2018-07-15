package com.github.config;


import com.github.realm.UserRealm;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by feel on 2017/10/10.
 */
@Configuration
public class ShiroConfig {
    @Autowired
    private UserRealm userRealm;

    /**
     * 验证用户
     * 可以声明多个Realm Bean，Shiro都会把它注入的
     *
     * @return
     */
    @Bean
    public Realm realm() {
//        TextConfigurationRealm realm = new TextConfigurationRealm();
//        //添加两个用户
//        //joe.coder=password 角色 user
//        //jill.coder=password 角色 admin
//        realm.setUserDefinitions("joe.coder=password,user\n" + "jill.coder=password,admin");
//        //设置角色admin的权限是read,write
//        //设置角色user的权限是read
//        realm.setRoleDefinitions("admin=read,write\n" + "user=read");
//        realm.setCachingEnabled(true);


        // uses 'classpath:shiro-users.properties' by default
        //PropertiesRealm realm = new PropertiesRealm();

        // Caching isn't needed in this example, but we can still turn it on
        // realm.setCachingEnabled(true);

        return userRealm;
    }

    @Bean
    public Authorizer authorizer() {
        return userRealm;
    }

//    @Bean
//    public SecurityManager securityManager() {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setRealm(userRealm);
//        return securityManager;
//    }


    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 配置shiro的url权限
     *
     * @return
     */
//    @Bean
//    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
//        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
//        chainDefinition.addPathDefinition("/login.html", "authc"); // need to accept POSTs from the login form
//        chainDefinition.addPathDefinition("/logout", "logout");
//        chainDefinition.addPathDefinition("/account-info", "perms[write]");//表示用户必需已通过认证,并拥有admin:edit权限才可以正常发起'/account-info'请求
////        chainDefinition.addPathDefinition("/account-info1", "roles[admin]");
////        chainDefinition.addPathDefinition("/account*", "perms[write]");
//        return chainDefinition;
//    }

//    @Bean
//    public CacheManager cacheManager() {
//        // Caching isn't needed in this example, but we will use the MemoryConstrainedCacheManager for this example.
//        return new MemoryConstrainedCacheManager();
//    }

    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        shiroFilter.setLoginUrl("/login.html");
        shiroFilter.setUnauthorizedUrl("/404.html");

        Map<String, String> filterMap = new LinkedHashMap<>();

        // 拦截器.
        //rest：比如/admins/user/**=rest[user],根据请求的方法，相当于/admins/user/**=perms[user：method] ,其中method为post，get，delete等。
        //port：比如/admins/user/**=port[8081],当请求的url的端口不是8081是跳转到schemal：//serverName：8081?queryString,其中schmal是协议http或https等，serverName是你访问的host,8081是url配置里port的端口，queryString是你访问的url里的？后面的参数。
        //perms：比如/admins/user/**=perms[user：add：*],perms参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，比如/admins/user/**=perms["user：add：*,user：modify：*"]，当有多个参数时必须每个参数都通过才通过，想当于isPermitedAll()方法。
        //roles：比如/admins/user/**=roles[admin],参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，当有多个参数时，比如/admins/user/**=roles["admin,guest"],每个参数通过才算通过，相当于hasAllRoles()方法。//要实现or的效果看http://zgzty.blog.163.com/blog/static/83831226201302983358670/
        //anon：比如/admins/**=anon 没有参数，表示可以匿名使用。
        //authc：比如/admins/user/**=authc表示需要认证才能使用，没有参数
        //authcBasic：比如/admins/user/**=authcBasic没有参数表示httpBasic认证
        //ssl：比如/admins/user/**=ssl没有参数，表示安全的url请求，协议为https
        //user：比如/admins/user/**=user没有参数表示必须存在用户，当登入操作时不做检查

//        filterMap.put("/api/**", "anon");
//        filterMap.put("/assets/**", "anon");
//        filterMap.put("/fonts/**", "anon");
//        filterMap.put("/maps/**", "anon");
//        filterMap.put("/scripts/**", "anon");
//        filterMap.put("/styles/**", "anon");
//        filterMap.put("/auth.html", "anon");
//        filterMap.put("/index.html", "anon");
//        filterMap.put("/**", "authc");

        filterMap.put("/logout", "logout");
        filterMap.put("/login.html", "authc");
        filterMap.put("/account-info", "perms[write]");
        filterMap.put("/**", "authc");
        shiroFilter.setFilterChainDefinitionMap(filterMap);

        return shiroFilter;
    }


}