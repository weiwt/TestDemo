package com.wwt.test.ses;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;

public class MyCredentialsProvider implements AWSCredentialsProvider {

    public AWSCredentials getCredentials() {
        return new AWSCredentials() {
            public String getAWSAccessKeyId() {
                return "AKIAIHPZNUCO3BHIOXWA";
            }

            public String getAWSSecretKey() {
                return "V/OuLsOn8dc3Of64FK6yrt+gA8m9lTFIKiWlXraR";
            }
        };
    }

    public void refresh() {
    }
}
