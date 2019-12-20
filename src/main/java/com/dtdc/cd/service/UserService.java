package com.dtdc.cd.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtdc.cd.exception.UserNotFoundException;
import com.dtdc.cd.model.User;
import com.dtdc.cd.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User saveUser(User user) {
		
		User user2 = new User();
		
		System.out.println("USERID : "+user2.getUserId());
		
		return userRepository.save(user);
	}
	
	public User getUserById(int userId) throws UserNotFoundException {
		
		Optional<User> option = userRepository.findById(userId);
		
		if(option.isPresent()) {
			
			return option.get();
		}
		
		throw new UserNotFoundException(userId+" is not Present in DB");
	}
}
