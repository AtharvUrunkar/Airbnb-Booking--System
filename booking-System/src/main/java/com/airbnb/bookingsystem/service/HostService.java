package com.airbnb.bookingsystem.service;

import com.airbnb.bookingsystem.entity.Host;
import com.airbnb.bookingsystem.entity.HostStatus;
import com.airbnb.bookingsystem.entity.User;
import com.airbnb.bookingsystem.repository.HostRepository;
import com.airbnb.bookingsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostService
{

	private final HostRepository hostRepository;
	private final UserRepository userRepository;

	public HostService(HostRepository hostRepository, UserRepository userRepository){
		this.hostRepository =hostRepository;
		this.userRepository = userRepository;
	}
	public Host createHost(Long userId) {

		// fetch user
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found"));

		// check if already host
		Optional<Host> existing = hostRepository.findByUserId(userId);
		if (existing.isPresent()) {
			throw new RuntimeException("Host already exists");
		}

		// create host
		Host host = new Host();
		host.setUser(user);
		host.setStatus(HostStatus.PENDING);

		return hostRepository.save(host);
	}

}
