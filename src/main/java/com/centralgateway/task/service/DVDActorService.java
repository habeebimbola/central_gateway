package com.centralgateway.task.service;

import com.centralgateway.task.DVDActor;
import com.centralgateway.task.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DVDActorService {

    @Autowired
    private final ActorRepository actorRepository;

    public DVDActorService(ActorRepository actorRepository) {
       this. actorRepository = actorRepository;
    }

    public DVDActor getActor(String name){
        return this.actorRepository.findByFirstName(name);
    }

}
