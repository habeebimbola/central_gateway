package com.centralgateway.task.events;

import com.centralgateway.task.SMSMessage;
import com.centralgateway.task.SMSResponse;
import org.springframework.context.ApplicationEvent;

public class SMSDeliveryEvent extends ApplicationEvent {

    private SMSResponse smsResponse;

    public SMSDeliveryEvent(SMSResponse smsResponse) {
        super(smsResponse);
        this.smsResponse = smsResponse;
    }

    public SMSResponse getSmsResponse() {
        return smsResponse;
    }
}
