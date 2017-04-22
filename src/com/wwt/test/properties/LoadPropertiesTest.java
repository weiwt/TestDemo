package com.wwt.test.properties;

import com.wwt.test.CommonTest;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class LoadPropertiesTest extends CommonTest {
    @Test
    public void loadTest() throws Exception{
        InputStream inputStream = new FileInputStream("D:\\demo\\TestDemo\\src\\com\\wwt\\test\\properties\\config.properties");

        Properties properties = new Properties();
        properties.load(inputStream);
        inputStream.close();

        String name = properties.getProperty("name");
        String age = properties.getProperty("age");

        sysOut(name);
        sysOut(age);
    }

    @Test
    public void loadTest2() throws Exception{

        InputStream inputStream = LoadPropertiesTest.class.getClassLoader().getResourceAsStream("com\\\\wwt\\\\test\\\\properties\\\\config.properties");


        Properties properties = new Properties();
        properties.load(inputStream);
        inputStream.close();

        String name = properties.getProperty("name");
        String age = properties.getProperty("age");

        sysOut(name);
        sysOut(age);
    }


    @Test
    public void loadTest3() throws Exception{

        InputStream inputStream = ClassLoader.getSystemResourceAsStream("com\\wwt\\test\\properties\\config.properties");

        Properties properties = new Properties();
        properties.load(inputStream);
        inputStream.close();

        String name = properties.getProperty("name");
        String age = properties.getProperty("age");

        sysOut(name);
        sysOut(age);
    }


    @Test
    public void loadTest4() throws Exception{

        InputStream inputStream = LoadPropertiesTest.class.getResourceAsStream("config.properties");

        Properties properties = new Properties();
        properties.load(inputStream);
        inputStream.close();

        String name = properties.getProperty("name");
        String age = properties.getProperty("age");

        sysOut(name);
        sysOut(age);
    }
}
