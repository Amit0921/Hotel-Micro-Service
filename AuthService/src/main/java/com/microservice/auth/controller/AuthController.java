package com.microservice.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.auth.entity.UserCredential;
import com.microservice.auth.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
	public String addNewUser(@RequestBody UserCredential user){
	return authService.saveUser(user);
	}
	
	@GetMapping("/token")
	public String getToken(UserCredential userCredential){
	return authService.generateToken(userCredential .getName());
	}

	@GetMapping("/validate")
	public String validateToken(@RequestParam("token") String token){
		authService.generateToken(token);
		return "Token Validated";
	}
}
