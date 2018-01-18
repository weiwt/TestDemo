package com.wwt.test.spring;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wwt
 * @date 2018/1/17 17:03
 */
public class Consumer2 implements InitializingBean {

    @Autowired(required = false)
    private List<TopicMessageExecutor> topicMessageExecutors = Lists.newArrayList();

    public void afterPropertiesSet() throws Exception {
        System.out.println("==========afterPropertiesSet===========");
        System.out.println(topicMessageExecutors.size());

        init();
    }

    public void init(){
        topicMessageExecutors.forEach(t -> {
            System.out.println(t.getTopic());
        });
//        for (TopicMessageExecutor topicMessageExecutor : topicMessageExecutors) {
//            System.out.println(topicMessageExecutor.getTopic());
//        }
    }
}
