package com.demo.livenation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.demo.livenation.entities.BookingDetails;
import com.demo.livenation.entities.Concert;
import com.demo.livenation.entities.User;
import com.demo.livenation.exceptions.IncorrectDetailsException;
import com.demo.livenation.exceptions.DetailsNotFoundException;
import com.demo.livenation.repository.BookingDetailsRepository;
//import com.demo.livenation.repository.BookingDetailsRepository;
import com.demo.livenation.repository.ConcertRepository;
import com.demo.livenation.repository.UserRepository;

@Service
public class ConcertServiceImpl implements ConcertService {

	@Autowired
	private ConcertRepository concertRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private BookingDetailsRepository bookRepo;
	
	@Override
	public Concert create(Long uId,Concert concert) throws IncorrectDetailsException,DetailsNotFoundException {
		if(concert.getNumberOfTickets()<10) {
			throw new IncorrectDetailsException("Number of tickets must be greater than or equal to 10");
		}
		User user = userRepo.findById(uId).orElseThrow(()-> new DetailsNotFoundException("User with id "+uId+" not found"));
		if(!user.isArtist())
			throw new IncorrectDetailsException("User is not an artist. Only an artist can create concerts;");
		concert.setUser(user);
		concert.setRemainingTickets(concert.getNumberOfTickets());
		Concert c =  concertRepo.save(concert);
		return c;
	}

	@Override
	public Concert getById(Long cId)throws DetailsNotFoundException {
		// TODO Auto-generated method stub
		return concertRepo.findById(cId).orElseThrow(()->new DetailsNotFoundException("Concert with id "+cId+" not found."));
	}

	@Override
	public List<Concert> getAllConcerts() {
		// TODO Auto-generated method stub
		return concertRepo.findAll();
	}

	@Override
	public Concert updateById(Long cId, Concert update) throws IncorrectDetailsException, DetailsNotFoundException {
		// TODO Auto-generated method stub
		if(update.getNumberOfTickets()<10) {
			throw new IncorrectDetailsException("Number of tickets must be greater than 10");
		}
		Concert concert = concertRepo.findById(cId).orElseThrow(()-> new DetailsNotFoundException("Concert not found with id "+cId));
		long ptc = concert.getNumberOfTickets();
		long ctc = update.getNumberOfTickets();
		long tickDiff = ctc-ptc;
		if(tickDiff<0) {
			throw new IncorrectDetailsException("Number of tickets cannot be reduced!");
		}
		concert.setRemainingTickets(concert.getRemainingTickets()+tickDiff);
		concert.setName(update.getName());
		concert.setNumberOfTickets(update.getNumberOfTickets());
		return concertRepo.save(concert);
	}

	@Override
	public void deleteById(Long cId) {
		// TODO Auto-generated method stub
		concertRepo.deleteById(cId);
		
	}

}
