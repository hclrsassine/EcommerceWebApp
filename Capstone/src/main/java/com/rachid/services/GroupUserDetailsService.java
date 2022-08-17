package com.rachid.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rachid.SendEmail;
import com.rachid.model.User;
import com.rachid.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GroupUserDetailsService implements UserDetailsService{
	
	private final static String user_Error = "user with email %s not found";
	private final UserRepository userRepo;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(user_Error, email)));
	}
	
	public String signUpUser(User user)
	{
		boolean userExists = userRepo.findByEmail(user.getEmail()).isPresent();
		
		if(userExists)
		{
			throw new IllegalStateException("email already taken");
		}
		
		String encodedPw = bCryptPasswordEncoder.encode(user.getPassword());
		
		user.setPassword(encodedPw);
		userRepo.save(user);
		
		SendEmail send = new SendEmail();
		send.sendEmail(user.getUsername(), user.getEmail());
		
		return "registration success for email : " + user.getEmail();
	}

}
