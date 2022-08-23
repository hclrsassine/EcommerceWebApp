package com.rachid.services;

import org.springframework.stereotype.Service;

import com.rachid.EmailValidator;
import com.rachid.RegistrationRequest;
import com.rachid.model.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegistrationService {
	
	private final EmailValidator emailValidator;
	private final GroupUserDetailsService groupUserDetailsService;
	
	public String register(RegistrationRequest request) {
		boolean isValidEmail = emailValidator.test(request.getEmail());
		
		if(!isValidEmail)
		{
			throw new IllegalStateException("email not valid");
		}
		return groupUserDetailsService.signUpUser(new User(null, request.getFirstName(), request.getLastName(),request.getUsername(), request.getEmail(),request.getPassword(), null));
	}

}
