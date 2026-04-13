package com.airbnb.bookingsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Table(name ="properties")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Property {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String location;

	private double pricePerNight;

	private int maxGuests;

	@ElementCollection
	private List<String> images;

	@ElementCollection
	private List<String> documents;

	@ManyToOne
	@JoinColumn(name ="host_id")
	private Host host;

	@OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
	private List<PropertyAvailability> availability;

}
