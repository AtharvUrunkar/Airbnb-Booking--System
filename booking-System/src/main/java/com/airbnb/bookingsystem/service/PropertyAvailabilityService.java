package com.airbnb.bookingsystem.service;

import com.airbnb.bookingsystem.entity.Property;
import com.airbnb.bookingsystem.entity.PropertyAvailbility;
import com.airbnb.bookingsystem.repository.PropertyAvailabilityRepository;
import com.airbnb.bookingsystem.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PropertyAvailabilityService {

	private final PropertyAvailabilityRepository availabilityRepository;
	private final PropertyRepository propertyRepository;

	public PropertyAvailabilityService(PropertyAvailabilityRepository availabilityRepository,
									   PropertyRepository propertyRepository) {
		this.availabilityRepository = availabilityRepository;
		this.propertyRepository = propertyRepository;
	}

	// 1️⃣ Add availability for date range
	public void addAvailability(Long propertyId, LocalDate startDate, LocalDate endDate) {

		// Fetch property
		Property property = propertyRepository.findById(propertyId)
				.orElseThrow(() -> new RuntimeException("Property not found"));

		LocalDate currentDate = startDate;

		while (!currentDate.isAfter(endDate)) {

			PropertyAvailbility availability = new PropertyAvailbility();
			availability.setProperty(property);
			availability.setDate(currentDate);
			availability.setAvailable(true);

			availabilityRepository.save(availability);

			currentDate = currentDate.plusDays(1);
		}
	}

	// 2️⃣ Check availability
	public boolean isAvailable(Long propertyId, LocalDate startDate, LocalDate endDate) {

		LocalDate currentDate = startDate;

		while (!currentDate.isAfter(endDate)) {

			PropertyAvailbility availability =
					(PropertyAvailbility) availabilityRepository.findByProperty_IdAndDate(propertyId, currentDate)
							.orElse(null);

			// If no record OR not available → fail
			if (availability == null || !availability.isAvailable()) {
				return false;
			}

			currentDate = currentDate.plusDays(1);
		}

		return true;
	}
}