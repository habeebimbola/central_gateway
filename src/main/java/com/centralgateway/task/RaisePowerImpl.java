package com.centralgateway.task;

import com.centralgateway.task.service.RaisePower;

public class RaisePowerImpl implements RaisePower {
    @Override
    public Double power(double x, double y) {
        return Math.pow(x,y);
    }
}
