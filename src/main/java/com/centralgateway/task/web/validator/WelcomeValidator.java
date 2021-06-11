package com.centralgateway.task.web.validator;

import com.centralgateway.task.DVDActor;
import com.centralgateway.task.SMSMessage;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public  class WelcomeValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return DVDActor.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        DVDActor dvdActor = (DVDActor)o;

        Integer.toBinaryString(12);
        if(dvdActor.getFirstName().isEmpty())
            errors.rejectValue("firstName","firstName.text","Firstname  Cannot Be Empty.");
    }
}
