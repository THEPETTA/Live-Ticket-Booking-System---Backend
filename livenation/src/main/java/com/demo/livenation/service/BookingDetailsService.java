package com.demo.livenation.service;

import java.util.List;

import com.demo.livenation.entities.BookingDetails;
import com.demo.livenation.exceptions.DetailsNotFoundException;
import com.demo.livenation.exceptions.IncorrectDetailsException;

public interface BookingDetailsService {
	List<BookingDetails> getAllBookings();
	BookingDetails getById(Long bId) throws DetailsNotFoundException;
	BookingDetails updateById(Long bId, Long cId, Long uId) throws IncorrectDetailsException, DetailsNotFoundException;
}
