package com.airbnb.bookingsystem.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Many bookings → one user
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	// Many bookings → one property
	@ManyToOne
	@JoinColumn(name = "property_id")
	private Property property;

	private LocalDate startDate;
	private LocalDate endDate;

	@Enumerated(EnumType.STRING)
	private BookingStatus status;

	// getters + setters
}