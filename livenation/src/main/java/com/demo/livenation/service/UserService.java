package com.demo.livenation.service;

import java.util.List;

import com.demo.livenation.entities.User;
import com.demo.livenation.exceptions.IncorrectDetailsException;
import com.demo.livenation.exceptions.UserAlreadyExistsException;
import com.demo.livenation.exceptions.DetailsNotFoundException;

public interface UserService {
	User create(User user)throws UserAlreadyExistsException, IncorrectDetailsException;
	User getById(Long uId)throws DetailsNotFoundException;
	List<User> getAllUsers();
	User updateById(Long uId, User update)throws DetailsNotFoundException, IncorrectDetailsException;
	void deleteById(Long uId);
}
