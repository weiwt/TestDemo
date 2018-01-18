package com.wwt.test.ses;/*
 * Copyright 2010-2017 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

import com.amazonaws.AmazonClientException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.DeleteTopicRequest;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.SubscribeRequest;

/**
 * This sample demonstrates how to make basic requests to Amazon SQS using the
 * AWS SDK for Java.
 * <p>
 * <b>Prerequisites:</b> You must have a valid Amazon Web
 * Services developer account, and be signed up to use Amazon SQS. For more
 * information on Amazon SQS, see http://aws.amazon.com/sqs.
 * <p>
 * Fill in your AWS access credentials in the provided credentials file
 * template, and be sure to move the file to the default location
 * (~/.aws/credentials) where the sample code will load the credentials from.
 * <p>
 * <b>WARNING:</b> To avoid accidental leakage of your credentials, DO NOT keep
 * the credentials file in your source directory.
 */
public class SnsSample {

    public static void main(String[] args) throws Exception {

        /*
         * The ProfileCredentialsProvider will return your [default]
         * credential profile by reading from the credentials file located at
         * (~/.aws/credentials).
         */
        MyCredentialsProvider credentialsProvider = new MyCredentialsProvider();
        try {
            credentialsProvider.getCredentials();
        } catch (Exception e) {
            throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. " +
                            "Please make sure that your credentials file is at the correct " +
                            "location (~/.aws/credentials), and is in valid format.",
                    e);
        }
        //create a new SNS client and set endpoint
        AmazonSNS snsClient = AmazonSNSClientBuilder.standard()
                .withCredentials(credentialsProvider)
                .withRegion(Regions.US_EAST_1)
                .build();

        //create a new SNS topic
//        CreateTopicRequest createTopicRequest = new CreateTopicRequest("MyNewTopic");
//        CreateTopicResult createTopicResult = snsClient.createTopic(createTopicRequest);
//        //print TopicArn
//        System.out.println(createTopicResult);
//        //get request id for CreateTopicRequest from SNS metadata
//        System.out.println("CreateTopicRequest - " + snsClient.getCachedResponseMetadata(createTopicRequest));

//        String topicArn = createTopicResult.getTopicArn();
//        subscribe(snsClient,"arn:aws:sns:us-east-1:857936942896:MyNewTopic");
//        publish(snsClient,"arn:aws:sns:us-east-1:857936942896:MyNewTopic");
        delete(snsClient,"arn:aws:sns:us-east-1:857936942896:MyNewTopic");
    }

    public static void subscribe(AmazonSNS snsClient,String topicArn){
        //subscribe to an SNS topic
        SubscribeRequest subRequest = new SubscribeRequest(topicArn, "email", "1327127023@qq.com");
        snsClient.subscribe(subRequest);
        //get request id for SubscribeRequest from SNS metadata
        System.out.println("SubscribeRequest - " + snsClient.getCachedResponseMetadata(subRequest));
        System.out.println("Check your email and confirm subscription.");
    }

    public static void publish(AmazonSNS snsClient,String topicArn){
        //publish to an SNS topic
        String msg = "My text published to SNS topic with email endpoint";
        PublishRequest publishRequest = new PublishRequest(topicArn, msg);
        PublishResult publishResult = snsClient.publish(publishRequest);
        //print MessageId of message published to SNS topic
        System.out.println("MessageId - " + publishResult.getMessageId());
    }

    public static void delete(AmazonSNS snsClient,String topicArn){
        //delete an SNS topic
        DeleteTopicRequest deleteTopicRequest = new DeleteTopicRequest(topicArn);
        snsClient.deleteTopic(deleteTopicRequest);
        //get request id for DeleteTopicRequest from SNS metadata
        System.out.println("DeleteTopicRequest - " + snsClient.getCachedResponseMetadata(deleteTopicRequest));
    }
}
