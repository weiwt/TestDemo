package com.wwt.test.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wwt
 * @date 2017/4/25 10:37
 */
@Service("shiroService")
public class ShiroService {

    @Autowired
    private SecurityManager securityManager;

    public void login(){
        UsernamePasswordToken token = new UsernamePasswordToken("javass","cc");
        token.setRememberMe(true);
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
    }

    @RequiresAuthentication
    @RequiresPermissions("p1")
    public void run(){
        System.out.println("==========ok===========");
    }
}
