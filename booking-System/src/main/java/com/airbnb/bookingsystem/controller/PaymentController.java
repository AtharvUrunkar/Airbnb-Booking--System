package com.airbnb.bookingsystem.controller;

import com.airbnb.bookingsystem.entity.Payment;
import com.airbnb.bookingsystem.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

	private final PaymentService paymentService;

	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@PostMapping
	public Payment processPayment(@RequestParam Long bookingId,
								  @RequestParam boolean success) {

		return paymentService.processPayment(bookingId, success);
	}
}