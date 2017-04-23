package com.wwt.test.shiro;

import com.wwt.test.CommonTest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class ShiroTest extends CommonTest {

    @Test
    public void test1(){
        Factory<SecurityManager> iniSecurityManagerFactory = new IniSecurityManagerFactory("classpath:TestShiro.ini");
        SecurityManager instance = iniSecurityManagerFactory.getInstance();
        SecurityUtils.setSecurityManager(instance);

        UsernamePasswordToken token = new UsernamePasswordToken("javass","cc");
        token.setRememberMe(true);

        boolean rememberMe = token.isRememberMe();
        sysOut(rememberMe);


        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
        } catch ( UnknownAccountException uae ) {
            //ignore
        } catch ( IncorrectCredentialsException ice ) {
            //ignore
        }catch (LockedAccountException lae ) {
            //ignore
        } catch (ExcessiveAttemptsException eae ) {
            //ignore
        }

        boolean p1 = currentUser.isPermitted("p1");
        sysOut(p1);
        boolean authenticated = currentUser.isAuthenticated();
        boolean remembered2 = currentUser.isRemembered();
        sysOut(remembered2);
        sysOut(authenticated);


        Object principal = currentUser.getPrincipal();
        String name = principal.getClass().getName();
        sysOut(name);
        sysOut(principal);

    }

    @Test
    public void realmTest(){

    }
}
