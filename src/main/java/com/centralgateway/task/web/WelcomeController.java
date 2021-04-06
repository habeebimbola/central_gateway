package com.centralgateway.task.web;

import com.centralgateway.task.DVDActor;
import com.centralgateway.task.SMSMessage;
import com.centralgateway.task.service.GlobalDAOService;
import com.centralgateway.task.web.validator.WelcomeValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@SessionAttributes("actor")
public class WelcomeController {

    private final Logger logger = LoggerFactory.getLogger(WelcomeController.class.getName());

    @Autowired
    private GlobalDAOService<DVDActor> globalDAOService;

    @Autowired
    private WelcomeValidator welcomeValidator;
//    private SMSMessageConverter smsMessageConverter;

    @RequestMapping( method = RequestMethod.GET , value = "/welcome")
    public String setupForm(Model model){
        DVDActor dvdActor = new DVDActor();
        model.addAttribute("actor", dvdActor);
        return "welcomeForm";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/allActors")
    public String showAllActors(Model model){

        model.addAttribute("allActorsList", this.globalDAOService.findAll() );
        return "allActorsForm";
    }
    @RequestMapping(method = RequestMethod.POST, value = "/welcome")
    public String submitForm(@Validated() @ModelAttribute("actor") DVDActor dvdActor,
                             BindingResult bindingResult,
                             SessionStatus sessionStatus){

        if(bindingResult.hasErrors())
        {
            bindingResult.getAllErrors().stream().forEach( (e)-> System.out.println(e.getDefaultMessage()));
            return "welcomeForm";
        }else
            {
                this.globalDAOService.save(dvdActor);
            }
        sessionStatus.setComplete();
        return "redirect:welcome";
    }
//    @ExceptionHandler
//    public String handleException(Model model, RuntimeException exception){
//        model.addAttribute("error",exception);
//        return "welcomeError";
//    }
//
    @InitBinder
    public void registerValidator(WebDataBinder webDataBinder){
        webDataBinder.setValidator(this.welcomeValidator);
    }
    @Component
    public class WelcomeValidator implements Validator{
    @Override
    public boolean supports(Class<?> aClass) {
        return DVDActor.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        DVDActor dvdActor = (DVDActor)o;

        if(dvdActor.getFirstName().isEmpty())
            errors.rejectValue("firstName","firstName.text","Firstname  Cannot Be Empty.");
    }
    }
//
//    @Component
//    class SMSMessageConverter implements Converter<SMSMessage, String>{
//
//        @Override
//        public String convert(SMSMessage smsMessage) {
//            return null;
//        }
//    }
}
