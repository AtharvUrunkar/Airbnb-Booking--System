package com.airbnb.bookingsystem.service;

import com.airbnb.bookingsystem.entity.Host;
import com.airbnb.bookingsystem.entity.HostStatus;
import com.airbnb.bookingsystem.entity.User;
import com.airbnb.bookingsystem.repository.HostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostService
{

	private final HostRepository hostRepository;

	public HostService(HostRepository hostRepository){
		this.hostRepository =hostRepository;
	}
	public Host createHost(Host host) {

		if (host == null) {
			throw new RuntimeException("Host cannot be null");
		}

		if (host.getUser() == null || host.getUser().getId() == null) {
			throw new RuntimeException("User is required");
		}

		Optional<Host> existingHost = hostRepository.findByUserId(host.getUser().getId());

		if (existingHost.isPresent()) {
			throw new RuntimeException("Host already exists for this user");
		}

		// ✅ set initial status
		host.setStatus(HostStatus.PENDING);

		return hostRepository.save(host);
	}

}
