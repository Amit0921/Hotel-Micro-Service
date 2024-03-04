package com.microservice.userservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.microservice.userservice.entities.User;

public interface UserRepository extends JpaRepository<User, String>{

}
