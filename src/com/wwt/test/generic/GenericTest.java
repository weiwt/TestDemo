package com.wwt.test.generic;

import com.wwt.test.CommonTest;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GenericTest extends CommonTest {

    private <T> void add(List<T> list) throws Exception{
//        Method get = list.getClass().getMethod("get", int.class);
//        Type genericReturnType = get.getGenericReturnType();
//        TypeVariableImpl GenericArrayTypeImpl = (TypeVariableImpl)genericReturnType;
////        Type type = genericReturnType.getActualTypeArguments()[0];
//        sysOut(genericReturnType.getTypeName());

        T t = list.get(0);
        String name = t.getClass().getName();
        sysOut(name);
    }

    @Test
    public void genericTest() throws Exception{
        Method add = GenericTest.class.getDeclaredMethod("add", List.class);
        Type[] genericParameterTypes = add.getGenericParameterTypes();

        for (Type p:genericParameterTypes){
            sysOut(p.getTypeName());
        }

        ParameterizedType genericParameterType = (ParameterizedType)genericParameterTypes[0];
        Type[] actualTypeArguments = genericParameterType.getActualTypeArguments();
        Type actualTypeArgument = actualTypeArguments[0];

        String typeName = actualTypeArgument.getTypeName();


        sysOut(typeName);
        sysOut(genericParameterType.getRawType());
        sysOut(genericParameterType.getOwnerType());

    }

    @Test
    public void genericTest2() throws Exception{
        List<String> retVal = new ArrayList<String>();
        retVal.add("wwt");
        add(retVal);

        List<Integer> retVal2 = new ArrayList<Integer>();
        retVal2.add(1);
        add(retVal2);
        sysOut(retVal2.size());
        assert false;//java 默认是关闭断言的
    }
}
