package com.wwt.test.base;

import org.junit.Test;

/**
 * @author wwt
 * @date 2017/9/12 11:44
 */
public class BaseTest {


    @Test
    public void intTest(){
        Integer a = Integer.valueOf(1234345234);
        Integer c = Integer.valueOf(1234345234);
        int b = 1234345234;
        System.out.println(a == c);
        System.out.println(a == b);
    }
}
