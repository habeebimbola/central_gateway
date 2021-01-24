package com.centralgateway.task.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class TransactionManagementAspect {

    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Pointcut("execution(* *.sendSMS(..))")
    public void readonly(){

    }

    @Pointcut("execution(* *.getDVDActor(..))")
    public void modifiable(){
    }


    @Before( "readonly()")
    public void logInsert(JoinPoint joinPoint){
        logger.info("A Before Advice Parameters For Method : {} are {}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()) );
    }

    @After(value = "readonly() && modifiable()")
    public void logDelete(JoinPoint joinPoint){
        logger.info("An After Advice Parameters For Method : {} are {}", joinPoint.getSignature().getName(), joinPoint.getArgs()[0].getClass() );
    }

//    @Pointcut("annotation(com.centralgateway.task.aspects.RecordLogger)")
//    public void recordLogger(){
//
//    }
    @AfterReturning( pointcut = "within(com.centralgateway.task.*)" , returning = "result")
    public void logSelect(JoinPoint joinPoint, Object result){
        logger.info("Successfully executed {} with Result: {}", joinPoint.getKind(), result );
    }

//    @Before("recordLogger()")
//    public void logChecker(JoinPoint joinPoint){
//        logger.info("Method within RecordLogger Annotation Invoked {}",joinPoint.getSignature().getName());
//    }
}
