package com.microservice.ratingservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.ratingservice.entities.Rating;
import com.microservice.ratingservice.repository.RatingRepository;

@Service
public class RatingServiceImpl implements RatingService{
	
	@Autowired
	private RatingRepository repository;

	@Override
	public Rating create(Rating rating) {
		String ratingId = UUID.randomUUID().toString();
		rating.setRatingId(ratingId);
		return repository.save(rating);
	}

	@Override
	public List<Rating> getRatings() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public List<Rating> getRatingsByUserId(String userId) {
		// TODO Auto-generated method stub
		return repository.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingsByHotelId(String hotelId) {
		// TODO Auto-generated method stub
		System.out.println(hotelId+"in service");
		return repository.findByHotelId(hotelId);
	}

}
