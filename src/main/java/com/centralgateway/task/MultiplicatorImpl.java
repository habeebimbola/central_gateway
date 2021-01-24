package com.centralgateway.task;

import com.centralgateway.task.service.Multiplicator;

public class MultiplicatorImpl implements Multiplicator {
    @Override
    public double multiply(double a, double b) {
        double result = a * b;
        return result;
    }
}
