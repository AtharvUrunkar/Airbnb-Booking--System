package com.airbnb.bookingsystem.controller;

import com.airbnb.bookingsystem.entity.Booking;
import com.airbnb.bookingsystem.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/bookings")
public class BookingController {

	private final BookingService bookingService;

	public BookingController(BookingService bookingService) {
		this.bookingService = bookingService;
	}

	// ✅ Create Booking API
	@PostMapping
	public Booking createBooking(@RequestParam Long userId,
								 @RequestParam Long propertyId,
								 @RequestParam String startDate,
								 @RequestParam String endDate) {

		return bookingService.createBooking(
				userId,
				propertyId,
				LocalDate.parse(startDate),
				LocalDate.parse(endDate)
		);
	}
}