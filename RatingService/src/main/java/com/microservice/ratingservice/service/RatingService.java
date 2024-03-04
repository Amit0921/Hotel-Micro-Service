package com.microservice.ratingservice.service;

import java.util.List;


import com.microservice.ratingservice.entities.Rating;


public interface RatingService {

	Rating create(Rating rating);

	List<Rating> getRatings();
	
	List<Rating> getRatingsByUserId(String userId);
	
	List<Rating> getRatingsByHotelId(String hotelId);

}
