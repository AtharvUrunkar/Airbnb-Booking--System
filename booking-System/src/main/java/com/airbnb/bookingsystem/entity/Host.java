package com.airbnb.bookingsystem.entity;

import jakarta.persistence.*;
import lombok.*;
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
}
