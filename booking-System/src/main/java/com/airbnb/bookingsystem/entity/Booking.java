package com.airbnb.bookingsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Entity
@Table(name = "bookings")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Many bookings → one user
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "user_id")
	private User user;

	// Many bookings → one property
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "property_id")
	private Property property;

	private LocalDate startDate;
	private LocalDate endDate;

	@Enumerated(EnumType.STRING)
	private BookingStatus status;

	// getters + setters
}