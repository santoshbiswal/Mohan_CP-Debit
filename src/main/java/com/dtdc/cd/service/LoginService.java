package com.dtdc.cd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtdc.cd.command.LoginCommand;
import com.dtdc.cd.exception.UserNotFoundException;
import com.dtdc.cd.model.Login;
import com.dtdc.cd.repository.LoginRepository;

@Service
public class LoginService {

	@Autowired
	private LoginRepository loginRepository;
	
	public Login saveLogin(Login login) {
		
		Login login2 = new Login();
		System.out.println("LoginID : "+login2.getLoginId());
		return loginRepository.save(login);
	}
	
	public Integer validateLogin(LoginCommand command) throws UserNotFoundException {
		
		Integer userId = loginRepository.getLoginCount(command.getUsername(), command.getPassword(), command.getUsertype());
		
		System.out.println("USERID : "+userId);
		
		if(userId == null) {
			
			throw new UserNotFoundException("Please Enter Valid Username/Password...");
		}
		
		return userId;
	}
}
