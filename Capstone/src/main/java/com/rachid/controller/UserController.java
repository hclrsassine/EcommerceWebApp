package com.rachid.controller;

import java.util.List;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rachid.RegistrationRequest;
import com.rachid.model.User;
import com.rachid.services.GroupUserDetailsService;
import com.rachid.services.RegistrationService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
	
	private RegistrationService registrationService;
	private GroupUserDetailsService groupDetailsService;
	
	
	@PostMapping("/registration")
	public String register(@RequestBody RegistrationRequest request)
	{
		return registrationService.register(request);
		
	}
	
	//For Admins only
	@GetMapping("/userlist")
	public List<User> viewUsers()
	{
		return groupDetailsService.listUsers();
		
	}
	
	@GetMapping("/editdetails")
	public User editDetails(User user, long userId) throws Exception
	{
		return groupDetailsService.updateUser(user, userId);
	}
}
