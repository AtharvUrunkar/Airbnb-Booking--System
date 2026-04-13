package com.airbnb.bookingsystem.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="Payment")
@Entity

public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name ="booking_id")
	private Booking booking;

	private double amount;

	@Enumerated(EnumType.STRING)
	private PaymentStatus status;

	private LocalDateTime paymentTime;

}
