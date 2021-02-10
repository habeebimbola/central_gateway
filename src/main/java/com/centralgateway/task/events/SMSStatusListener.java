package com.centralgateway.task.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SMSStatusListener  {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @EventListener
    public void onApplicationEvent(SMSDeliveryEvent smsDeliveryEvent){
        logger.info("Another Attached Listener{}",smsDeliveryEvent.getSmsResponse().getResponseMessage());
    }
}
