package com.centralgateway.task.repository;

import com.centralgateway.task.DVDActor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<DVDActor,Integer> {

    public DVDActor findByFirstName(String firstName);
}
