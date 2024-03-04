package com.microservice.userservice.services;

import java.util.List;

import com.microservice.userservice.entities.User;

public interface UserService {

	User saveUser(User user);
	
	List<User> getAllUser();
	
	User getUser(String UserId);
	
	
}
