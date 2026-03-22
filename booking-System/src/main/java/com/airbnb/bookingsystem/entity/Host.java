package com.airbnb.bookingsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "hosts")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class Host {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String VerficationStatus;

	@OneToOne
	@JoinColumn(name="user_id")
	private User user;

	@OneToMany(mappedBy = "host")
	private List<Property> properties;

	@Enumerated(EnumType.STRING)
	private HostStatus status;
}
