package com.wwt.test.java8;

import com.wwt.test.CommonTest;
import org.junit.Test;
import java.util.*;
/**
 * @author wwt
 * @date 2017/8/4 18:49
 */
public class Java8Test extends CommonTest{

    /**
     * ofNullable 返回 Optional对象 如果传入参数是个NULL对象,仍然返回Optional,而 of 则会抛出 npe
     */
    @Test
    public void optionalTest(){
        Optional<Object> o = Optional.ofNullable(null);
        sysOut(o);
    }
}
