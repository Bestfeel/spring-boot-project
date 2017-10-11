
### shiro-spring-boot-starter相关配置

```

shiro: 
  web: 
    ## 开启shiro web自动化配置，默认开启
    enabled: true
  loginUrl: /login.jsp
  successUrl: /
  ## 必须要配置为授权的url,否则在无权限的情况下，会找不到未授权url,导致找不到安全管理器（SecurityManager）
  unauthorizedUrl: null
  ## session管理方式，true使用shiro提供的session管理，false则使用servlet提供的session管理
  userNativeSessionManager: false
  ## 会话管理
  sessionManager: 
    sessionIdCookieEnabled: true
    sessionIdUrlRewritingEnabled: true
    deleteInvalidSessions: true
    cookie: 
      name: JSESSIONID
      maxAge: -1
      domain: null
      path: null
      secure: false
  ## 记住我管理
  rememberMeManager: 
    cookie: 
      name: rememberMe
      ## 默认一年
      maxAge: 60 * 60 * 24 * 365
      domain: null
      path: null
      secure: false

```


