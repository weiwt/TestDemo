package com.wwt.test.spring.impl;

import com.amazonaws.services.sqs.model.Message;
import com.wwt.test.spring.TopicMessageExecutor;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author wwt
 * @date 2018/1/18 14:50
 */
@Component
public class TestTopicMessageExecutorImpl implements TopicMessageExecutor {

    private static final String QUEUE_NAME = "MyQueue";
    public String getTopic() {
        return QUEUE_NAME;
    }

    public void execute(Message message) {
        System.out.println("============execute message==========");
        System.out.println("    MessageId:     " + message.getMessageId());
        System.out.println("    ReceiptHandle: " + message.getReceiptHandle());
        System.out.println("    MD5OfBody:     " + message.getMD5OfBody());
        System.out.println("    Body:          " + message.getBody());
        for (Map.Entry<String, String> entry : message.getAttributes().entrySet()) {
            System.out.println("  Attribute");
            System.out.println("    Name:  " + entry.getKey());
            System.out.println("    Value: " + entry.getValue());
        }
    }
}
