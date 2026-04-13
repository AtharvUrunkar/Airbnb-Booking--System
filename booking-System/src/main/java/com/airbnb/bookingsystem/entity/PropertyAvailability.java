package com.airbnb.bookingsystem.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name ="property_availability")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyAvailbility {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate date;

	private boolean available;

	@ManyToOne
	@JoinColumn(name = "property_id")
	private Property property;
}
