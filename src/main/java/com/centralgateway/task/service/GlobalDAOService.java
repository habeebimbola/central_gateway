package com.centralgateway.task.service;

import com.centralgateway.task.DVDActor;

import java.util.Collection;
import java.util.List;

public interface GlobalDAOService<T> {

    void save(T t);
    T getOne(Long id);
    void update(DVDActor dvdActor);
    List<T> findAll();
    void updateList(List<T> list);
}
