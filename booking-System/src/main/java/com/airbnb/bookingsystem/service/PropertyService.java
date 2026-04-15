package com.airbnb.bookingsystem.service;

import com.airbnb.bookingsystem.entity.Host;
import com.airbnb.bookingsystem.entity.HostStatus;
import com.airbnb.bookingsystem.entity.Property;
import com.airbnb.bookingsystem.entity.User;
import com.airbnb.bookingsystem.repository.HostRepository;
import com.airbnb.bookingsystem.repository.PropertyRepository;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

	private final PropertyRepository propertyRepository;

	private final HostRepository hostRepository;
	public PropertyService(HostRepository hostRepository,PropertyRepository propertyRepository){
		this.hostRepository =hostRepository;
		this.propertyRepository = propertyRepository;
	}

	public Property createProperty(Long hostId, Property property) {

		if (property == null) {
			throw new RuntimeException("Property cannot be null");
		}

		// 1️⃣ Fetch host
		Host host = hostRepository.findById(hostId)
				.orElseThrow(() -> new RuntimeException("Host not found"));

		// 2️⃣ Validate host approval
		if (host.getStatus() != HostStatus.APPROVED) {
			throw new RuntimeException("Host is not approved");
		}

		// 3️⃣ Attach host
		property.setHost(host);

		// 4️⃣ Save
		return propertyRepository.save(property);
	}

	}

