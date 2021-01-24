package com.centralgateway.task.aspects;

import com.centralgateway.task.CalculatorImpl;
import com.centralgateway.task.MultiplicatorImpl;
import com.centralgateway.task.RaisePowerImpl;
import com.centralgateway.task.service.CalculatorService;
import com.centralgateway.task.service.Multiplicator;
import com.centralgateway.task.service.RaisePower;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Aspect
@Component("arithmeticIntroduction")
public class ArithmeticIntroduction {

    @DeclareParents(value = "com.centralgateway.task.arithmetic.*", defaultImpl = CalculatorImpl.class)
    public CalculatorService calculatorService;

    @DeclareParents(value = "com.centralgateway.task.arithmetic.*", defaultImpl = MultiplicatorImpl.class)
    public Multiplicator multiplicator;

    @DeclareParents(value = "com.centralgateway.task.arithmetic.*", defaultImpl = RaisePowerImpl.class)
    public RaisePower raisePower;

}
