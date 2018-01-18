package com.wwt.test.spring;

import com.amazonaws.services.sqs.model.Message;

/**
 * @author wwt
 * @date 2018/1/17 17:03
 */
public interface TopicMessageExecutor{
    void execute(Message message);
    String getTopic();
}
