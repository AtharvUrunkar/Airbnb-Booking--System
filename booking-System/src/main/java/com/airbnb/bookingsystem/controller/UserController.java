package com.airbnb.bookingsystem.controller;

import com.airbnb.bookingsystem.entity.User;
import com.airbnb.bookingsystem.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	// ✅ Create User API
	@PostMapping
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
}