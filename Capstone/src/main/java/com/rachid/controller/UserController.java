package com.rachid.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rachid.RegistrationRequest;
import com.rachid.services.RegistrationService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {
	
	private RegistrationService registrationService;
	
	@PostMapping("/registration")
	public String register(@RequestBody RegistrationRequest request)
	{
		return registrationService.register(request);
	}
}
