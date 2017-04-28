package com.wwt.test;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CommonTest {

    public ApplicationContext appContext ;

    @Before
    public void getContext(){
        appContext = new ClassPathXmlApplicationContext("spring-service.xml");
    }

    public void sysOut(Object o){
        System.out.println(o);
    }
}
