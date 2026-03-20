package com.airbnb.bookingsystem.service;


import com.airbnb.bookingsystem.entity.User;
import com.airbnb.bookingsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User createUser(User user) {
		Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
		if (existingUser.isPresent()) {
			throw new RuntimeException("User with this email already Exists");
		}
		return userRepository.save(user);
	}
     public List<User> getAllUsers(){
		return userRepository.findAll();
	 }
	 public User getUserId(Long id){
		return userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found"));
	 }


}

