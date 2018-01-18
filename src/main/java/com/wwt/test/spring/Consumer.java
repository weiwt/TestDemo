package com.wwt.test.spring;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.wwt.test.ses.MyCredentialsProvider;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


/**
 * @author wwt
 * @date 2018/1/17 17:03
 */
public class Consumer implements InitializingBean {

    @Autowired(required = false)
    private List<TopicMessageExecutor> topicMessageExecutors = Lists.newArrayList();

    private int streamNumPerTopic = 1;
    private ExecutorService executor;

    public void afterPropertiesSet() throws Exception {
        System.out.println("==========afterPropertiesSet===========");
        System.out.println(topicMessageExecutors.size());

        try {
            List<TopicMessageExecutor> hasTopicMessageExecutors = Optional.ofNullable(topicMessageExecutors)
                    .orElse(Collections.emptyList()).stream()
                    .filter(m -> !StringUtils.isEmpty(m.getTopic()))
                    .collect(toList());
            if (CollectionUtils.isEmpty(hasTopicMessageExecutors)) {
                System.out.println("hasTopicMessageExecutors is zero!");
                return;
            }
            Multimap<String, TopicMessageExecutor> topicMessageExecutorMultimap = Multimaps.index(hasTopicMessageExecutors, TopicMessageExecutor::getTopic);
            Set<String> topicSet = topicMessageExecutorMultimap.keySet();

            executor = Executors.newFixedThreadPool(topicSet.size() * streamNumPerTopic);
//
            Multimap<String, TopicMessageExecutor> topic2MessageExecutorsMap =
                    Multimaps.index(hasTopicMessageExecutors, TopicMessageExecutor::getTopic);

            AmazonSQS sqs = AmazonSQSClientBuilder.standard()
                    .withCredentials(new MyCredentialsProvider())
                    .withRegion(Regions.US_EAST_1)
                    .build();

            for (String queue : topicSet) {
                executor.submit(() ->{
                    ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queue);
                    while (true){
                        try {
                            List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
                            if (messages.size() == 0){
                                continue;
                            }
                            System.out.println("receive message from queue named :"+queue);
                            System.out.println("messaegs size is :"+messages.size());
                            Collection<TopicMessageExecutor> topicMessageExecutors = topic2MessageExecutorsMap.get(queue);
                            messages.forEach(message -> {
                                topicMessageExecutors.forEach(executor ->{
                                    executor.execute(message);
                                    //TODO delete message
                                });
                            });
                        }catch (Exception e){
                            continue;
                        }
                    }

                });

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
