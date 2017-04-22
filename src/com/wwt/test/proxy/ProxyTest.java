package com.wwt.test.proxy;

import com.wwt.test.CommonTest;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;

public class ProxyTest extends CommonTest {

    @Test
    public void proxyTest1(){
        Class<?> proxyClass = Proxy.getProxyClass(Collection.class.getClassLoader(), Collection.class);
        sysOut(proxyClass.getName());

        Constructor<?>[] constructors = proxyClass.getConstructors();
        for (Constructor c : constructors){
            Class[] parameterTypes = c.getParameterTypes();
            String name1 = c.getName();
            StringBuilder sb = new StringBuilder();
            sb.append(name1);
            sb.append("(");

            for (Class cc : parameterTypes){
                String name = cc.getName();
                sb.append(name);
                sb.append(",");
            }

            if (parameterTypes.length > 0)
                sb.deleteCharAt(sb.lastIndexOf(","));
            sb.append(")");
            sb.append("\n");
            sysOut(sb.toString());
        }


        Method[] methods = proxyClass.getMethods();
        for (Method m : methods){
            Class[] parameterTypes = m.getParameterTypes();
            String name1 = m.getName();
            StringBuilder sb = new StringBuilder();
            sb.append(name1);
            sb.append("(");

            for (Class cc : parameterTypes){
                String name = cc.getName();
                sb.append(name);
                sb.append(",");
            }

            if (parameterTypes.length > 0)
                sb.deleteCharAt(sb.lastIndexOf(","));
            sb.append(")");
            sb.append("\n");
            sysOut(sb.toString());
        }
    }

    @Test
    public void proxyTest2() throws Exception{
        Class<?> proxyClass = Proxy.getProxyClass(Collection.class.getClassLoader(), Collection.class);
        Constructor<?> constructor = proxyClass.getConstructor(InvocationHandler.class);
        Collection o = (Collection) constructor.newInstance(new InvocationHandler() {
            private Collection target = new ArrayList();
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return method.invoke(target,args);
            }
        });
        o.add(1);
        sysOut(o.size());
    }
}
