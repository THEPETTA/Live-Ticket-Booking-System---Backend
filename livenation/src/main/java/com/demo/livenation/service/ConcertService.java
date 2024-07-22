package com.demo.livenation.service;

import java.util.List;

import com.demo.livenation.entities.Concert;
import com.demo.livenation.exceptions.IncorrectDetailsException;
import com.demo.livenation.exceptions.DetailsNotFoundException;

public interface ConcertService {
	Concert create(Long uId,Concert concert)throws IncorrectDetailsException, DetailsNotFoundException;
	public Concert getById(Long cId)throws DetailsNotFoundException;
	List<Concert> getAllConcerts();
	Concert updateById(Long cId, Concert update) throws IncorrectDetailsException, DetailsNotFoundException;
	void deleteById(Long cId);
}
