package com.microservice.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.auth.entity.UserCredential;

public interface UserCredRepository extends JpaRepository<UserCredential, Integer>{

}
