package com.centralgateway.task.web;

import com.centralgateway.task.SMSMessage;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/welcome")
@SessionAttributes("welcome")
public class WelcomeController {

    private WelcomeValidator welcomeValidator;
    private SMSMessageConverter smsMessageConverter;

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(Model model){
        return "welcomeForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submitForm(BindingResult bindingResult,
                             @Validated @ModelAttribute("welcome") SMSMessage smsMessage,
                             SessionStatus sessionStatus){

        sessionStatus.setComplete();
        return "redirect:welcome";
    }
    @ExceptionHandler
    public String handleException(Model model, RuntimeException exception){
        model.addAttribute("error",exception);
        return "welcomeError";
    }

    @InitBinder
    public void registerValidator(WebDataBinder webDataBinder){
        webDataBinder.setValidator(this.welcomeValidator);
    }

    @Component
    class WelcomeValidator implements Validator{
        @Override
        public boolean supports(Class<?> aClass) {
            return SMSMessage.class.isAssignableFrom(aClass);
        }

        @Override
        public void validate(Object o, Errors errors) {
        }
    }

    @Component
    class SMSMessageConverter implements Converter<SMSMessage, String>{

        @Override
        public String convert(SMSMessage smsMessage) {
            return null;
        }
    }
}
