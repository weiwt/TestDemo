package com.wwt.test.annotation;

import com.wwt.test.CommonTest;
import org.junit.Test;

import java.lang.annotation.Annotation;

@MyAnnotation
public class AnnotationTest extends CommonTest{

    @Test
    public void test1(){
        boolean annotationPresent = AnnotationTest.class.isAnnotationPresent(MyAnnotation.class);
        if (annotationPresent) {
            MyAnnotation annotation = AnnotationTest.class.getAnnotation(MyAnnotation.class);
            Class<? extends Annotation> aClass = annotation.annotationType();
            sysOut(aClass.getName());
        }
    }
}
