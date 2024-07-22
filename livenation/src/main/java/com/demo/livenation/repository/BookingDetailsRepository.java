package com.demo.livenation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.livenation.entities.BookingDetails;

public interface BookingDetailsRepository extends JpaRepository<BookingDetails, Long> {
}
