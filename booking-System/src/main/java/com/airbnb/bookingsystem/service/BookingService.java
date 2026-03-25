package com.airbnb.bookingsystem.service;


import com.airbnb.bookingsystem.entity.PropertyAvailbility;
import com.airbnb.bookingsystem.repository.BookingRepository;
import com.airbnb.bookingsystem.repository.PropertyAvailabilityRepository;
import com.airbnb.bookingsystem.repository.PropertyRepository;
import com.airbnb.bookingsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

	private final BookingRepository bookingRepository;
    private final PropertyRepository propertyRepository;
	private final UserRepository userRepository;
	private final PropertyAvailabilityRepository propertyAvailabilityRepository;

	public BookingService(BookingRepository bookingRepository,PropertyRepository propertyRepository, UserRepository userRepository, PropertyAvailabilityRepository propertyAvailabilityRepository){
		this.bookingRepository=bookingRepository;
		this.propertyAvailabilityRepository = propertyAvailabilityRepository;
		this.userRepository = userRepository;
		this.propertyRepository = propertyRepository;
	}
}
