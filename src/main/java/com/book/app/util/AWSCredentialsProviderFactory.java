package com.book.app.util;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;

import static com.amazonaws.SDKGlobalConfiguration.ACCESS_KEY_ENV_VAR;
import static com.amazonaws.SDKGlobalConfiguration.SECRET_KEY_ENV_VAR;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class AWSCredentialsProviderFactory {
    private AWSCredentialsProviderFactory() {
    }

    public static AWSCredentialsProvider awsCredentialsProvider() {
        if (isNotEmpty(System.getenv(ACCESS_KEY_ENV_VAR)) && isNotEmpty(System.getenv(SECRET_KEY_ENV_VAR))) {
            return new EnvironmentVariableCredentialsProvider();
        }
        return new ProfileCredentialsProvider("default");
    }
}
