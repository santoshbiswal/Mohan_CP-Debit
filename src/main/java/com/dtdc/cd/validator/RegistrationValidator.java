package com.dtdc.cd.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.dtdc.cd.command.RegistrationCommand;

@Component
public class RegistrationValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		if (clazz.isAssignableFrom(RegistrationCommand.class))
			return true;

		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {

		RegistrationCommand command = (RegistrationCommand) target;

		if (command.getAddressLine1() == null || "".equals(command.getAddressLine1())
				|| command.getAddressLine1().isEmpty()) {

			errors.rejectValue("addressLine1", "registration.addressLine1");
		}

		if (command.getAddressLine2() == null || "".equals(command.getAddressLine2())
				|| command.getAddressLine2().isEmpty()) {

			errors.rejectValue("addressLine2", "registration.addressLine2");
		}

		if (command.getCity() == null || "".equals(command.getCity())
				|| command.getCity().isEmpty()) {

			errors.rejectValue("city", "registration.city");
		}

		if (command.getState() == null || "".equals(command.getState())
				|| command.getState().isEmpty()) {

			errors.rejectValue("state", "registration.state");
		}

		if (command.getCountry() == null || "".equals(command.getCountry())
				|| command.getCountry().isEmpty()) {

			errors.rejectValue("country", "registration.country");
		}

		if (command.getZip() <= 0) {

			errors.rejectValue("zip", "registration.zip");
		}

		if (command.getName() == null || "".equals(command.getName())
				|| command.getName().isEmpty()) {

			errors.rejectValue("name", "registration.name");
		}
		
		if (command.getPhone() == null || "".equals(command.getPhone())
				|| command.getPhone().isEmpty()) {

			errors.rejectValue("phone", "registration.phone");
		}

		if (command.getEmail() == null || "".equals(command.getEmail())
				|| command.getEmail().isEmpty()) {

			errors.rejectValue("email", "registration.email");
		}

		if (command.getDob() == null || "".equals(command.getDob())) {

			errors.rejectValue("dob", "registration.dob");
		}

		if (command.getUsername() == null || "".equals(command.getUsername())
				|| command.getUsername().isEmpty()) {

			errors.rejectValue("username", "registration.username");
		}

		if (command.getPassword() == null || "".equals(command.getPassword())
				|| command.getPassword().isEmpty()) {

			errors.rejectValue("password", "registration.password");
		}

		/*
		 * if (command.getConfirmpass() == null || "".equals(command.getAddressLine1())
		 * || command.getAddressLine1().isEmpty()) {
		 * 
		 * errors.rejectValue("confirmpass", "registration.confirmpass"); }
		 */

		if (command.getUsertype() == null || "".equals(command.getUsertype())
				|| command.getUsertype().isEmpty()) {

			errors.rejectValue("usertype", "registration.usertype");
		}

	}

}
