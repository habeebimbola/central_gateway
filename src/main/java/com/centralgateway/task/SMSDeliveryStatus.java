package com.centralgateway.task;

import com.centralgateway.task.events.SMSDeliveryEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SMSDeliveryStatus  {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void smsDeliveryStatus(SMSResponse response){
        SMSDeliveryEvent smsDeliveryEvent = new SMSDeliveryEvent(response);
        this.applicationEventPublisher.publishEvent(smsDeliveryEvent);
    }
}
