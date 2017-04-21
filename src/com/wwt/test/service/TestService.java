package com.wwt.test.service;

import org.springframework.stereotype.Service;

/**
 * @author wwt
 * @date 2017/4/18 18:20
 */
@Service("testService")
public class TestService {

    public void run() throws Exception{
        for (int i = 0; i< 10; i++){
            Thread.sleep(100);
            System.out.println(i);
        }
    }
}
