package com.wwt.test.aspectj;

import com.wwt.test.CommonTest;
import com.wwt.test.service.TestService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wwt
 * @date 2017/4/18 11:42
 */
public class AspectJTest extends CommonTest{

    @Test
    public void aspectTest() throws Exception{
        ApplicationContext appContext = new ClassPathXmlApplicationContext("spring-service.xml");
        TestService testService = (TestService) appContext.getBean("testService");
        testService.run();
    }
}
