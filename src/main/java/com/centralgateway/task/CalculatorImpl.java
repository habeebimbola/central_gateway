package com.centralgateway.task;

import com.centralgateway.task.service.CalculatorService;

public class CalculatorImpl implements CalculatorService {

    @Override
    public double add(double a, double b) {
        double result = a + b;
        return result;
    }

    @Override
    public double subtract(double a, double b) {
        double result = a - b;

        return result;
    }
}
