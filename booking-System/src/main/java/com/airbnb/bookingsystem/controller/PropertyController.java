package com.airbnb.bookingsystem.controller;

import com.airbnb.bookingsystem.entity.Property;
import com.airbnb.bookingsystem.service.PropertyService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/properties")
public class PropertyController {

	private final PropertyService propertyService;

	public PropertyController(PropertyService propertyService) {
		this.propertyService = propertyService;
	}

	@PostMapping
	public Property createProperty(@RequestParam Long hostId,
								   @RequestBody Property property) {

		return propertyService.createProperty(hostId,property);
	}
}