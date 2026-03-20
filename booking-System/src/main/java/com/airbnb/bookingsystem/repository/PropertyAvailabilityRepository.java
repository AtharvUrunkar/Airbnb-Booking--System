package com.airbnb.bookingsystem.repository;


import com.airbnb.bookingsystem.entity.PropertyAvailbility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyAvailabilityRepository
		extends JpaRepository<PropertyAvailbility, Long> {

}