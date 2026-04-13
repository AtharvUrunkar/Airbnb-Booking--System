package com.airbnb.bookingsystem.service;

import com.airbnb.bookingsystem.entity.*;
import com.airbnb.bookingsystem.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class PaymentService {

	private final PaymentRepository paymentRepository;
	private final BookingRepository bookingRepository;
	private final PropertyAvailabilityRepository availabilityRepository;

	public PaymentService(PaymentRepository paymentRepository,
						  BookingRepository bookingRepository,
						  PropertyAvailabilityRepository availabilityRepository) {
		this.paymentRepository = paymentRepository;
		this.bookingRepository = bookingRepository;
		this.availabilityRepository = availabilityRepository;
	}

	public Payment processPayment(Long bookingId, boolean success) {

		// 1️⃣ Fetch booking
		Booking booking = bookingRepository.findById(bookingId)
				.orElseThrow(() -> new RuntimeException("Booking not found"));

		// 2️⃣ Validate booking state
		if (booking.getStatus() == BookingStatus.CONFIRMED) {
			throw new RuntimeException("Booking already confirmed");
		}

		if (booking.getStatus() == BookingStatus.CANCELLED) {
			throw new RuntimeException("Booking already cancelled");
		}

		// 3️⃣ Create payment object
		Payment payment = new Payment();
		payment.setBooking(booking);
		payment.setPaymentTime(LocalDateTime.now());
		payment.setAmount(0); // you can calculate later

		// 4️⃣ Handle success / failure
		if (success) {

			// ✅ SUCCESS → confirm booking
			booking.setStatus(BookingStatus.CONFIRMED);
			payment.setStatus(PaymentStatus.SUCCESS);

		} else {

			// ❌ FAILED → cancel booking
			booking.setStatus(BookingStatus.CANCELLED);
			payment.setStatus(PaymentStatus.FAILED);

			// 🔥 Restore availability
			LocalDate currentDate = booking.getStartDate();

			while (!currentDate.isAfter(booking.getEndDate())) {

				PropertyAvailability availability =
						(PropertyAvailability) availabilityRepository.findByProperty_IdAndDate(
								booking.getProperty().getId(), currentDate
						).orElseThrow(() -> new RuntimeException("Availability not found"));

				availability.setAvailable(true);
				availabilityRepository.save(availability);

				currentDate = currentDate.plusDays(1);
			}
		}

		// 5️⃣ Save booking update
		bookingRepository.save(booking);

		// 6️⃣ Save payment
		return paymentRepository.save(payment);
	}
}