package com.centralgateway.task.configuration;

import com.centralgateway.task.BannerLoader;
import com.centralgateway.task.SMSDeliveryInterceptor;
import com.centralgateway.task.SMSMessage;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import javax.xml.bind.DatatypeConverter;


import java.util.*;

@Configuration
@Profile("local")
@EnableScheduling
public class  UserConfiguration implements WebMvcConfigurer  {

    private final static Logger logger = LoggerFactory.getLogger(UserConfiguration.class);

    @Autowired
    private ContentNegotiationManager contentNegotiationManager;

    @Autowired()
    @Qualifier("smsInterceptor")
    private SMSDeliveryInterceptor smsDeliveryInterceptor;

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

    @Bean()
    public BannerLoader getBannerLoader(){
        logger.info("Resource FileName: "+resource.getFilename()) ;
        BannerLoader bannerLoader = new BannerLoader();
        bannerLoader.setFileName(resource);
        return bannerLoader;
    }

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry){
        interceptorRegistry.addInterceptor(smsDeliveryInterceptor).addPathPatterns("/sendSMS");
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        Map<String, MediaType>  mediaTypeMap= new HashMap<>();
        mediaTypeMap.put("html", MediaType.TEXT_HTML);
        mediaTypeMap.put("pdf", MediaType.valueOf("application/pdf"));
        mediaTypeMap.put("pdf", MediaType.valueOf("application/vnd.excel"));
        mediaTypeMap.put("pdf", MediaType.valueOf("application/xml"));

        configurer.mediaTypes(mediaTypeMap);
    }

    @Bean
    public ContentNegotiatingViewResolver contentNegotiatingViewResolver()
    {
        ContentNegotiatingViewResolver contentNegotiatingViewResolver = new ContentNegotiatingViewResolver();
        contentNegotiatingViewResolver.setOrder(0);
        contentNegotiatingViewResolver.setContentNegotiationManager(this.contentNegotiationManager);
        return contentNegotiatingViewResolver;
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add((handlerExceptionResolver()));
    }

    @Bean
    public HandlerExceptionResolver handlerExceptionResolver()
    {
        Properties exceptionMapping = new Properties();
        exceptionMapping.setProperty("","");
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
        exceptionResolver.setExceptionMappings(exceptionMapping);
        exceptionResolver.setDefaultErrorView("error");

        Objects.equals(null, null);

        return  exceptionResolver;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new DateFormatter());
    }


}
