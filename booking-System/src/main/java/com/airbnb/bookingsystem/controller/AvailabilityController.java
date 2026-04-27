package com.airbnb.bookingsystem.controller;

import com.airbnb.bookingsystem.entity.PropertyAvailability;
import com.airbnb.bookingsystem.service.PropertyAvailabilityService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/availability")
public class AvailabilityController {

	private final PropertyAvailabilityService availabilityService;

	public AvailabilityController(PropertyAvailabilityService availabilityService) {
		this.availabilityService = availabilityService;
	}
	@PostMapping
	public String addAvailability(@RequestParam Long propertyId,
								  @RequestParam String startDate,
								  @RequestParam String endDate) {

		availabilityService.addAvailability(
				propertyId,
				LocalDate.parse(startDate),
				LocalDate.parse(endDate)
		);

		return "Availability added successfully";
	}	}

