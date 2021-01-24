package com.centralgateway.task;

public class Wrapper<T> {

    private Wrapper() { }

    public static <T> Wrapper<T> create(T content) {
        Wrapper<T> v = null;
        return v;
    }
}
