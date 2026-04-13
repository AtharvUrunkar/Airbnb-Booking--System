package com.airbnb.bookingsystem.service;

import com.airbnb.bookingsystem.entity.*;
import com.airbnb.bookingsystem.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class BookingService {

	private final BookingRepository bookingRepository;
	private final PropertyRepository propertyRepository;
	private final UserRepository userRepository;
	private final PropertyAvailabilityRepository availabilityRepository;

	// ✅ Constructor Injection
	public BookingService(BookingRepository bookingRepository,
						  PropertyRepository propertyRepository,
						  UserRepository userRepository,
						  PropertyAvailabilityRepository availabilityRepository) {
		this.bookingRepository = bookingRepository;
		this.propertyRepository = propertyRepository;
		this.userRepository = userRepository;
		this.availabilityRepository = availabilityRepository;
	}

	// ✅ Concurrency + Transaction Safe
	@Transactional
	public synchronized Booking createBooking(Long userId,
											  Long propertyId,
											  LocalDate startDate,
											  LocalDate endDate) {

		// 1️⃣ Fetch user
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found"));

		// 2️⃣ Fetch property
		Property property = propertyRepository.findById(propertyId)
				.orElseThrow(() -> new RuntimeException("Property not found"));

		// 3️⃣ Check availability
		LocalDate currentDate = startDate;

		while (!currentDate.isAfter(endDate)) {
			// logic
			currentDate = currentDate.plusDays(1);
		}

		// 4️⃣ Create booking
		Booking booking = new Booking();
		booking.setUser(user);
		booking.setProperty(property);
		booking.setStartDate(startDate);
		booking.setEndDate(endDate);
		booking.setStatus(BookingStatus.PENDING);

		// 5️⃣ Block dates (CRITICAL)
		currentDate = startDate;

		while (!currentDate.isAfter(endDate)) {

			PropertyAvailability availability =
					availabilityRepository.findByProperty_IdAndDate(propertyId, currentDate)
							.orElseThrow(() -> new RuntimeException("Not found"));

			availability.setAvailable(false);
			availabilityRepository.save(availability);

			currentDate = currentDate.plusDays(1);
		}

		// 6️⃣ Save booking
		return bookingRepository.save(booking);
	}
}