package com.airbnb.bookingsystem.repository;

import com.airbnb.bookingsystem.entity.Host;
import com.airbnb.bookingsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HostRepository extends JpaRepository<Host, Long> {
	Optional<Host> findById(Long userid);

	Optional<Host> findByUserId(Long id);
}