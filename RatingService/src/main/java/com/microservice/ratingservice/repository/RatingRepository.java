package com.microservice.ratingservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.ratingservice.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating, String> {

	// Custom find a method
	List<Rating> findByUserId(String userId);

	List<Rating> findByHotelId(String hotelId);

}
