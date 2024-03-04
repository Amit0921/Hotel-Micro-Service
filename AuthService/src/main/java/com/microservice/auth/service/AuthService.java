package com.microservice.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.microservice.auth.entity.UserCredential;
import com.microservice.auth.repository.UserCredRepository;

@Service
public class AuthService {

	@Autowired
	private UserCredRepository credRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	public String saveUser(UserCredential userCredential) {
		userCredential.setPassowrd(passwordEncoder.encode(userCredential.getPassowrd()));
		credRepository.save(userCredential);
		return "User Added Successfully";
	}
	
	public String generateToken(String userName) {
		return jwtService.generateToken(userName);
		
	}
	
	public void validateToken(String token) {
		jwtService.validateToken(token);
	}
	
}
