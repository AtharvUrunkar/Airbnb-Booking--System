package com.airbnb.bookingsystem.service;

import com.airbnb.bookingsystem.entity.Property;
import com.airbnb.bookingsystem.entity.PropertyAvailability;
import com.airbnb.bookingsystem.repository.PropertyAvailabilityRepository;
import com.airbnb.bookingsystem.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PropertyAvailabilityService {

	private final PropertyAvailabilityRepository availabilityRepository;
	private final PropertyRepository propertyRepository;

	public PropertyAvailabilityService(PropertyAvailabilityRepository availabilityRepository,
									   PropertyRepository propertyRepository) {
		this.availabilityRepository = availabilityRepository;
		this.propertyRepository = propertyRepository;
	}

	// ✅ Add availability for date range
	public void addAvailability(Long propertyId, LocalDate startDate, LocalDate endDate) {

		Property property = propertyRepository.findById(propertyId)
				.orElseThrow(() -> new RuntimeException("Property not found"));

		LocalDate currentDate = startDate;

		while (!currentDate.isAfter(endDate)) {

			// 🔥 CHECK duplicate
			Optional<PropertyAvailability> existing =
					availabilityRepository.findByProperty_IdAndDate(propertyId, currentDate);

			if (existing.isEmpty()) {
				PropertyAvailability availability = new PropertyAvailability();
				availability.setProperty(property);
				availability.setDate(currentDate);
				availability.setAvailable(true);

				availabilityRepository.save(availability);
			}

			currentDate = currentDate.plusDays(1);
		}
	}

	// ✅ Check availability
	public boolean isAvailable(Long propertyId, LocalDate startDate, LocalDate endDate) {

		LocalDate currentDate = startDate;

		while (!currentDate.isAfter(endDate)) {

			PropertyAvailability availability =
					availabilityRepository.findByProperty_IdAndDate(propertyId, currentDate)
							.orElse(null);

			// ❌ If missing OR not available → reject
			if (availability == null || !availability.isAvailable()) {
				return false;
			}

			currentDate = currentDate.plusDays(1);
		}

		return true;
	}
}