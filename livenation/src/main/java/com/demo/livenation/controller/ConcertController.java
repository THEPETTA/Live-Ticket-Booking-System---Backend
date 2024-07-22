package com.demo.livenation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.livenation.entities.Concert;
import com.demo.livenation.entities.User;
import com.demo.livenation.exceptions.DetailsNotFoundException;
import com.demo.livenation.exceptions.IncorrectDetailsException;
import com.demo.livenation.service.ConcertService;

@RestController
@RequestMapping("/concert")
public class ConcertController {

	@Autowired
	private ConcertService cs;
	
	public Map<String,Object> concertResponse(Concert c){
		Map<String, Object> concertMap = new HashMap<String, Object>();
		Map<String, Object> userMap = new HashMap<String, Object>();
		concertMap.put("cId", c.getcId());
		concertMap.put("name", c.getName());
		concertMap.put("numberOfTickets", c.getNumberOfTickets());
		User u = c.getUser();
		userMap.put("uId", u.getuId());
		userMap.put("name", u.getName());
		userMap.put("email", u.getEmail());
		userMap.put("artist", u.isArtist());
		concertMap.put("artistDetails", userMap);
		return concertMap;
	}
	
	@PostMapping("/by/{uId}")
	public ResponseEntity<Map<String, Object>> createConcert(@PathVariable Long uId, @RequestBody Concert concert)throws IncorrectDetailsException,DetailsNotFoundException {
		return ResponseEntity.ok(concertResponse(cs.create(uId,concert)));
	}
	
	@GetMapping("/{cId}")
	public ResponseEntity<Map<String, Object>> getById(@PathVariable Long cId) throws DetailsNotFoundException{
		return ResponseEntity.ok(concertResponse(cs.getById(cId)));
	}
	
	@GetMapping()
	public ResponseEntity<List<Map<String, Object>>> getAll(){
		List<Map<String, Object>> con = new ArrayList<>();
		for(Concert c:cs.getAllConcerts()) {
			con.add(concertResponse(c));
		}
		return ResponseEntity.ok(con);
	}
	
	@PutMapping("/{cId}")
	public ResponseEntity<Map<String, Object>> updateById(@RequestBody Concert concert, @PathVariable Long cId)throws IncorrectDetailsException, DetailsNotFoundException{
		return ResponseEntity.ok(concertResponse(cs.updateById(cId, concert)));
	}
	
	@DeleteMapping("/{cId}")
	public void deleteById(@PathVariable Long cId) {
		cs.deleteById(cId);
	}
}
