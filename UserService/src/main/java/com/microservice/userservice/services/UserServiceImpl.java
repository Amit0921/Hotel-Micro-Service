package com.microservice.userservice.services;

import java.util.List;
import java.util.UUID;
import java.util.Arrays;
import java.util.stream.Collectors;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.userservice.entities.Hotel;
import com.microservice.userservice.entities.Rating;
import com.microservice.userservice.entities.User;
import com.microservice.userservice.exception.ResourceNotFoundException;
import com.microservice.userservice.externalservices.HotelService;
import com.microservice.userservice.repositories.UserRepository;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
    private HotelService hotelService;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		String randUUID = UUID.randomUUID().toString();
		user.setUserId(randUUID);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		// get user from database with the help user repository
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found !! "+userId));
		
		// fetch rating of above user from rating service
		// http://localhost:8082/ratings/users/3d273485-8956-445a-90b4-d38f7dc2e442
		Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
//        logger.info("{} ", ratingsOfUser);
        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
        List<Rating> ratingList = ratings.stream().map(rating -> {
            //api call to hotel service to get the hotel
            //http://localhost:8082/hotels/1cbaf36d-0b28-4173-b5ea-f1cb0bc0a791
        	// Here Rest Template is implemented to call external API of Hotel Service
//          ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
//          Hotel hotel = forEntity.getBody();
        	
        	// Here Feign Client is implemented to call external API of Hotel Service
        	Hotel hotel = hotelService.getHotel(rating.getHotelId());

            //logger.info("response status code: {} ",forEntity.getStatusCode());
            //set the hotel to rating
            rating.setHotel(hotel);
            //return the rating
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
		
		return user;
	}
	
}
