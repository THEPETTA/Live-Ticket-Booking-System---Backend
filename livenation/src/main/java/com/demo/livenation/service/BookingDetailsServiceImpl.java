package com.demo.livenation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.livenation.entities.BookingDetails;
import com.demo.livenation.entities.Concert;
import com.demo.livenation.entities.User;
import com.demo.livenation.exceptions.DetailsNotFoundException;
import com.demo.livenation.exceptions.IncorrectDetailsException;
import com.demo.livenation.repository.BookingDetailsRepository;
import com.demo.livenation.repository.ConcertRepository;
import com.demo.livenation.repository.UserRepository;

@Service
public class BookingDetailsServiceImpl implements BookingDetailsService{
	
	@Autowired
	private BookingDetailsRepository bookRepo;
	@Autowired
	private ConcertRepository concertRepo;
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public List<BookingDetails> getAllBookings() {
		// TODO Auto-generated method stub
		return bookRepo.findAll();
	}

	@Override
	public BookingDetails getById(Long bId) throws DetailsNotFoundException {
		// TODO Auto-generated method stub
		return bookRepo.findById(bId).orElseThrow(()-> new DetailsNotFoundException("Booking with ID "+bId+" not found"));
	}

	@Override
	public BookingDetails updateById(Long bId,Long cId, Long uId) throws IncorrectDetailsException, DetailsNotFoundException {
		// TODO Auto-generated method stub
		if(concertRepo.existsById(cId)) {
			User u = userRepo.findById(uId).orElseThrow(()-> new DetailsNotFoundException("User with ID "+uId+" not found"));
			BookingDetails b =  bookRepo.findById(bId).orElseThrow(()-> new DetailsNotFoundException("Booking with ID "+bId+" not found"));
			b.setUser(u);
			System.out.println(b);
			return bookRepo.save(b);
		}
		else
			throw new DetailsNotFoundException("Concert with ID "+cId+" not found");
	}
	
	public BookingDetails create(BookingDetails b,Long cId, Long uId) throws DetailsNotFoundException {
		Concert c = concertRepo.findById(cId).orElseThrow(()-> new DetailsNotFoundException("Concert with ID "+cId+" not found"));
		User u  = userRepo.findById(uId).orElseThrow(()-> new DetailsNotFoundException("User with ID "+uId+" not found"));
		b.setConcert(c);
		b.setUser(u);
		long tickDiff = c.getRemainingTickets();
		c.setRemainingTickets(tickDiff-1);
		concertRepo.save(c);
		return bookRepo.save(b);
	}

}
