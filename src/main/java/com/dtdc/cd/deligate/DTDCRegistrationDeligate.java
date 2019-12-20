package com.dtdc.cd.deligate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dtdc.cd.command.RegistrationCommand;
import com.dtdc.cd.model.Address;
import com.dtdc.cd.model.Login;
import com.dtdc.cd.model.User;
import com.dtdc.cd.service.AddressService;
import com.dtdc.cd.service.LoginService;
import com.dtdc.cd.service.UserService;

@Component
public class DTDCRegistrationDeligate {

	@Autowired
	private AddressService addressService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LoginService loginService;
	
	public boolean register(RegistrationCommand command) {
		
		User ul = extractUserFromCommand(command);
		Address al = extractAddressFromCommand(command);
		Login log = extractLoginFromCommand(command);
		
		User user = userService.saveUser(ul);
		
		log.setUser(ul);
		loginService.saveLogin(log);
		
		al.setUser(user);
		addressService.saveAddress(al);
		
		return true;
	}
	
	private Login extractLoginFromCommand(RegistrationCommand command) {
		
		Login login = new Login();
		
		login.setUsername(command.getUsername());
		login.setPassword(command.getPassword());
		login.setRole("USER");
		login.setUsertype(command.getUsertype());
		
		return login;
	}
	
	private User extractUserFromCommand(RegistrationCommand command) {
		
		User user = new User();
		
		user.setName(command.getName());
		user.setPhone(command.getPhone());
		user.setEmail(command.getEmail());
		user.setDob(command.getDob());	
		
		return user;
	}
	
	public Address extractAddressFromCommand(RegistrationCommand command) {
		
		Address address = new Address();
		
		address.setAddressLine1(command.getAddressLine1());
		address.setAddressLine2(command.getAddressLine2());
		address.setCity(command.getCity());
		address.setCountry(command.getCountry());
		address.setState(command.getState());
		address.setZip(command.getZip());
		
		return address;
	}
}
