package com.centralgateway.task;

import com.centralgateway.task.aspects.ArithmeticIntroduction;
import com.centralgateway.task.configuration.UserConfiguration;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Import(UserConfiguration.class)
public class TaskApplication {
	private final static Logger logger = LoggerFactory.getLogger(TaskApplication.class);

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(TaskApplication.class, args);
		SMSMessage smsMessage = context.getBean("smsMessage", SMSMessage.class);
		ArithmeticIntroduction arithmeticIntroduction = context.getBean("arithmeticIntroduction", ArithmeticIntroduction.class);
	}

}
