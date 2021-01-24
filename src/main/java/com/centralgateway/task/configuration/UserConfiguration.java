package com.centralgateway.task.configuration;

import com.centralgateway.task.BannerLoader;
import com.centralgateway.task.SMSMessage;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
@Profile("local")
@EnableScheduling
public class  UserConfiguration {

    private final static Logger logger = LoggerFactory.getLogger(UserConfiguration.class);

    @Value("classpath:hello.txt")
    private Resource resource;

    @Value("${sms.username}")
    private String smsUsername;

    @Value("${sms.password}")
    private String smsPassword;
    @Lazy
    @Bean(name = "smsMessage", initMethod = "welcome", destroyMethod = "goodBye")
    public SMSMessage getSMSMessage(){
        return new SMSMessage();
    }

    @Lazy
    @Bean
    public RestTemplate getRestTemplate(){
        CloseableHttpClient httpClient
                = HttpClients.custom()
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory
                = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        return 	new RestTemplateBuilder().
                basicAuthorization(smsUsername,smsPassword).
                requestFactory(requestFactory.getClass()).
                build();
    }

    @Bean
    public BannerLoader getBannerLoader(){
        logger.info("Resource FileName: "+resource.getFilename()) ;
        BannerLoader bannerLoader = new BannerLoader();
        bannerLoader.setFileName(resource);
        return bannerLoader;
    }
}
