package com.microservice.hotelservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.hotelservice.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String> {

}
