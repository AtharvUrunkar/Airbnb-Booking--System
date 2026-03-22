package com.airbnb.bookingsystem.service;

import com.airbnb.bookingsystem.entity.Host;
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

	public Property createProperty(Property property){

		if (property == null) {
			throw new RuntimeException("Property cannot be null");
		}

		if (property.getHost() == null) {
			throw new RuntimeException("Host is required to create Property");
		}

		if (property.getHost().getId() == null) {
			throw new RuntimeException("Host ID is required");
		}
		Long hostId = property.getHost().getId();

		// 3️⃣ Fetch Host from DB
		Host host = hostRepository.findById(hostId)
				.orElseThrow(() -> new RuntimeException("Host not found with id: " + hostId));

		// 4️⃣ Attach managed Host entity
		property.setHost(host);
  //After adding the property host becomes real to the system no matter it is accepted by admin as a host if you dont have registered property system not consider it one.!
		// 5️⃣ Save Property
		return propertyRepository.save(property);


	}

	}

