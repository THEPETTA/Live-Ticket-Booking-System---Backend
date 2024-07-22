package com.demo.livenation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.livenation.entities.BookingDetails;
import com.demo.livenation.entities.Concert;
import com.demo.livenation.entities.User;
import com.demo.livenation.exceptions.DetailsNotFoundException;
import com.demo.livenation.exceptions.IncorrectDetailsException;
import com.demo.livenation.service.BookingDetailsServiceImpl;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;

@RestController
@RequestMapping("/booking")
public class BookingDetailsController {
	@Autowired
	public BookingDetailsServiceImpl bs;
	
	public Map<String,Object> bookResponse(BookingDetails b){
		Map<String, Object> bookMap = new HashMap<String, Object>();
		Map<String, Object> concertMap = new HashMap<String, Object>();
		Map<String, Object> userMap = new HashMap<String, Object>();
		Concert c = b.getConcert();
		User u = b.getUser();
		concertMap.put("cId", c.getcId());
		concertMap.put("name", c.getName());
		userMap.put("uId", u.getuId());
		userMap.put("name", u.getName());
		userMap.put("email", u.getEmail());
		bookMap.put("bId", b.getbId());
		bookMap.put("seatNo", b.getSeatNo());
		bookMap.put("bookerDetails", userMap);
		bookMap.put("concertDetails", concertMap);
		return bookMap;
	}
	
	@GetMapping
	public List<Map<String, Object>> getAll() {
		List<Map<String,Object>> ab = new ArrayList<>();
		for(BookingDetails b: bs.getAllBookings()) {
			ab.add(bookResponse(b));
		}
		return ab;
	}
	@PostMapping("{cId}/{uId}")
	public ResponseEntity<Map<String, Object>> updateById(@RequestBody BookingDetails b, @PathVariable Long cId, @PathVariable Long uId) throws IncorrectDetailsException, DetailsNotFoundException {
		return ResponseEntity.ok(bookResponse(bs.create(b, cId, uId)));
	}
	@GetMapping("/{bId}")
	public ResponseEntity<Map<String, Object>> getById(@PathVariable Long bId) throws DetailsNotFoundException {
		return ResponseEntity.ok(bookResponse(bs.getById(bId)));
	}
}
