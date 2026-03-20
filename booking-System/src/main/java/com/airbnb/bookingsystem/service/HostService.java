package com.airbnb.bookingsystem.service;

import com.airbnb.bookingsystem.entity.Host;
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

		// 1️⃣ Null safety checks
		if (host == null) {
			throw new RuntimeException("Host cannot be null");
		}

		if (host.getUser() == null) {
			throw new RuntimeException("User is required to create host");
		}

		if (host.getUser().getId() == null) {
			throw new RuntimeException("User ID is required");
		}

		// 2️⃣ Check if host already exists for this user
		Optional<Host> existingHost = hostRepository.findByUserId(host.getUser().getId());

		if (existingHost.isPresent()) {
			throw new RuntimeException("Host already exists for this user");
		}

		// 3️⃣ Save host
		return hostRepository.save(host);
	}
}
