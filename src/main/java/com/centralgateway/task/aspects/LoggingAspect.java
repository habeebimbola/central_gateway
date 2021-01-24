package com.centralgateway.task.aspects;

import com.centralgateway.task.SMSMessage;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Order(0)
public class LoggingAspect  {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @After("execution(* *.getDVDActor(..))")
    public void logAction(JoinPoint joinPoint){
        logger.info("This Logging Aspect Signals Method Starting....{} {}",
                joinPoint.getSignature().getName(),
                joinPoint.getSignature().getDeclaringTypeName());
    }

    @AfterReturning(pointcut = "execution(* *.method(..))", returning = "result")
    public void logAfterAction(JoinPoint joinPoint, Object result){
        logger.info("After Returning Method Execution...{}", result);
    }

    @AfterThrowing(pointcut = "execution(* *.function(String, Double))", throwing= "exception")
    public void logException(JoinPoint joinPoint, Exception exception){
        logger.debug("This method threw this exception {}", exception.getMessage());

    }

    @Before("execution(* *.method(Integer, java.math.BigDecimal))")
    public void logBeforeExecuting(JoinPoint joinPoint){
        logger.info("This method is executed before PointCut execution.");
    }

    @Around("execution(* *.sendSMS(..)) && target(target) && args(smsMessage)")
    public void logAround(ProceedingJoinPoint proceedingJoinPoint, Object target, SMSMessage smsMessage) throws Throwable {
        logger.info("Around Advice PointCut {} {} {}", smsMessage.getText(), smsMessage.getPhone(), target.getClass().getName());
        proceedingJoinPoint.proceed();
    }
}
