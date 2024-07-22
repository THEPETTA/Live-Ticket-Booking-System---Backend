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
import com.demo.livenation.exceptions.IncorrectDetailsException;
import com.demo.livenation.exceptions.UserAlreadyExistsException;
import com.demo.livenation.exceptions.DetailsNotFoundException;
import com.demo.livenation.service.UserServiceImpl;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserServiceImpl us;
	
	public Map<String,Object> concertResponse(Concert c){
		Map<String, Object> concertMap = new HashMap<String, Object>();
		concertMap.put("cId", c.getcId());
		concertMap.put("name", c.getName());
		concertMap.put("numberOfTickets", c.getNumberOfTickets());
		return concertMap;
	}
	public Map<String,Object> userResponse(User u){
		Map<String, Object> userMap = new HashMap<String, Object>();
		List<Map<String,Object>> concerts = new ArrayList<>();
		userMap.put("uId", u.getuId());
		userMap.put("name", u.getName());
		userMap.put("email", u.getEmail());
		userMap.put("password", u.getPassword());
		userMap.put("artist", u.isArtist());
		for(Concert c:u.getConcerts()) {
			concerts.add(concertResponse(c));
		}
		userMap.put("concerts", concerts);
		return userMap;
	}
	
	@GetMapping
	public ResponseEntity<List<Map<String, Object>>> getAllUsers(){
		List<Map<String,Object>> users = new ArrayList<>();
		for(User u: us.getAllUsers()) {
			users.add(userResponse(u));
		}
		return ResponseEntity.ok(users);
	}
	@GetMapping("/{uId}")
	public ResponseEntity<Map<String, Object>> getById(@PathVariable Long uId)throws DetailsNotFoundException{
		return ResponseEntity.ok(userResponse(us.getById(uId)));
	}
	@PostMapping
	public User createUser(@RequestBody User user)throws UserAlreadyExistsException, IncorrectDetailsException {
		return us.create(user);
	}
	
	@PutMapping("/{uId}")
	public ResponseEntity<Map<String, Object>> updateById(@PathVariable Long uId, @RequestBody User update) throws DetailsNotFoundException, IncorrectDetailsException{
		return ResponseEntity.ok(userResponse(us.updateById(uId, update)));
	}
	
	@DeleteMapping("/{uId}")
	public ResponseEntity<User> deleteById(@PathVariable Long uId) {
		us.deleteById(uId);
		return ResponseEntity.noContent().build();
	}
}
