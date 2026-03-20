package com.airbnb.bookingsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;
@Entity
@Table(name = " users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(unique = true)
	private String email;

	private String password;
}
