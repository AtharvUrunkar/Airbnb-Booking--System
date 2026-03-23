package com.airbnb.bookingsystem.repository;

import com.airbnb.bookingsystem.entity.Property;
import com.airbnb.bookingsystem.entity.PropertyAvailbility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {



}