package com.airbnb.bookingsystem.controller;

import com.airbnb.bookingsystem.entity.Host;
import com.airbnb.bookingsystem.service.HostService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hosts")
public class HostController {

	private final HostService hostService;

	public HostController(HostService hostService) {
		this.hostService = hostService;
	}

	@PostMapping
	public Host createHost(@RequestParam Long userId) {
		return hostService.createHost(userId);
	}
}