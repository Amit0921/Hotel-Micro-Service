package com.microservice.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.userservice.entities.User;
import com.microservice.userservice.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User user1 = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
	
//	used for @Retry
//	int retryCount = 1;

	@GetMapping("/{userId}")
//	@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallBack")
//	@Retry(name = "ratingHotelService", fallbackMethod ="ratingHotelFallBack")
	@RateLimiter(name = "userRatelimiter", fallbackMethod = "ratingHotelFallBack")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
//		logger.info("Retry count {}",retryCount);
//		retryCount++;
		User user = userService.getUser(userId);
		return ResponseEntity.ok(user);
	}
	

//	 creating fall back method for circuit-breaker
	public ResponseEntity<User> ratingHotelFallBack(String userId, Exception ex) {
        logger.info("Fallback is executed because service is down : ", ex.getMessage());
		ex.printStackTrace();
		User user = User.builder()
				.email("dummy@gmail.com")
				.name("Dummy")
				.about("This user is created dummy because some service is down")
				.userId("141234")
				.build();
		return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
	}

	@GetMapping
	public ResponseEntity<List<User>> getAllUser() {
		List<User> allUser = userService.getAllUser();
		return ResponseEntity.ok(allUser);
	}
}
