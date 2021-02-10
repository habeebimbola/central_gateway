package com.centralgateway.task.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SMSDeliveryListener  {

    private final Logger logger = LoggerFactory.getLogger(SMSDeliveryListener.class);

    @EventListener
    public void onApplicationEvent(SMSDeliveryEvent event) {
        logger.info("SMSDeliveryStatus: {} {}",event.getSmsResponse().getResponseMessage(), event.getSmsResponse().getResponseCode());
    }
}
