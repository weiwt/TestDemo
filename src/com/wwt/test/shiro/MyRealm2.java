package com.wwt.test.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

public class MyRealm2 extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String) getAvailablePrincipal(principalCollection);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Set<String> p = new HashSet<String>();
        p.add("p1");
        p.add("p2");
        info.setStringPermissions(p);

        Set<String> r = new HashSet<String>();
        r.add("r1");
        r.add("r2");
        info.setRoles(r);

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        char[] password = token.getPassword();
        String s = String.valueOf(password);
        //跟数据库中的进行比较

        if ("javass".equals(username))
            throw new AuthenticationException("myRealm2 认证失败");

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,s,getName());

        return info;
    }
}
