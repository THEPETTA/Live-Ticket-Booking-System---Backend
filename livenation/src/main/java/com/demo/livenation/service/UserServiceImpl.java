package com.demo.livenation.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.livenation.entities.User;
import com.demo.livenation.exceptions.IncorrectDetailsException;
import com.demo.livenation.exceptions.UserAlreadyExistsException;
import com.demo.livenation.exceptions.DetailsNotFoundException;
import com.demo.livenation.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User create(User user) throws UserAlreadyExistsException, IncorrectDetailsException {
		// TODO Auto-generated method stub
		if(user.getEmail().matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
			if(user.getPassword().length()>=8) {
				if(!userRepository.existsByEmail(user.getEmail()))
					return userRepository.save(user);
				else
					throw new UserAlreadyExistsException("User with the email "+user.getEmail()+" already exists");
			}
			else
				throw new IncorrectDetailsException("Password must be 8 charecters long");
		}
		else
			throw new IncorrectDetailsException("Enter a valid email");
	}

	@Override
	public User getById(Long uId) throws DetailsNotFoundException {
		// TODO Auto-generated method stub
		return userRepository.findById(uId).orElseThrow(()-> new DetailsNotFoundException("User with id "+uId+" not found"));
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User updateById(Long uId, User update) throws DetailsNotFoundException,IncorrectDetailsException {
		// TODO Auto-generated method stub
		if(update.getPassword().length()<8) {
			throw new IncorrectDetailsException("Password must be 8 charecters long");
		}
		return userRepository.findById(uId)
				.map(user -> {
					user.setName(update.getName());
					user.setPassword(update.getPassword());
					user.setArtist(update.isArtist());
					return userRepository.save(user);
				}).orElseThrow(()-> new DetailsNotFoundException("User not found with id "+uId));
	}

	@Override
	public void deleteById(Long uId) {
		// TODO Auto-generated method stub
		userRepository.deleteById(uId);
	}

}
