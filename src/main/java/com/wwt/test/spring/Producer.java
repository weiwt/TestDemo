package com.wwt.test.spring;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.sun.istack.internal.NotNull;
import com.wwt.test.ses.MyCredentialsProvider;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * @author wwt
 * @date 2018/1/18 16:07
 */
@Service
public class Producer implements InitializingBean {

    private AmazonSQS sqs;

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            sqs = AmazonSQSClientBuilder.standard()
                    .withCredentials(new MyCredentialsProvider())
                    .withRegion(Regions.US_EAST_1)
                    .build();
        }catch (Exception e){
            e.printStackTrace();
        }

    }



    public void send(@NotNull String queueName, @NotNull String message){
        String queueUrl = createQueue(queueName);
        sqs.sendMessage(new SendMessageRequest(queueUrl, message));
    }

    private String createQueue(String queueName){
        CreateQueueRequest createQueueRequest =   new CreateQueueRequest(queueName);
        String queueUrl = sqs.createQueue(createQueueRequest).getQueueUrl();
        System.out.println("create aws queue return queueUrl :"+queueUrl);
        return queueUrl;
    }
}
