package com.microservice.hotelservice.services;

import java.util.List;

import com.microservice.hotelservice.entities.Hotel;

public interface HotelService {

	Hotel create(Hotel hotel);
	
	List<Hotel> getAll();
	
	Hotel get(String id);
}
