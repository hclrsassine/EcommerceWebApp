package com.rachid.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rachid.GroupUserDetails;
import com.rachid.SendEmail;
import com.rachid.model.Role;
import com.rachid.model.User;
import com.rachid.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GroupUserDetailsService implements UserDetailsService{
	
	private final static String user_Error = "username with email %s not found";
	private final UserRepository userRepo;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepo.findByUsername(username);
        return user.map(GroupUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " was not found."));
	}
	
	public String signUpUser(User user)
	{
		boolean userExists = userRepo.findByUsername(user.getUsername()).isPresent();
		
		if(userExists)
		{
			throw new IllegalStateException("username taken already taken");
		}
		
		String encodedPw = bCryptPasswordEncoder.encode(user.getPassword());
		
		user.setPassword(encodedPw);
		user.getRoles().add(new Role(null, "ADMIN", null));
		userRepo.save(user);
		
		SendEmail send = new SendEmail();
		send.sendEmail(user.getUsername(), user.getEmail());
		
		return "registration success for email : " + user.getEmail();
	}
	
	public List<User> listUsers()
	{
		return userRepo.findAll();
	}
	
	public User updateUser(User user, long id) throws Exception
	{
		User u = userRepo.findById(id).get();
		if(u.getUserId()!=0)
		{
			u.setFirstName(user.getFirstName());
			u.setLastName(user.getLastName());
			u.setEmail(user.getEmail());
			u.setUsername(user.getUsername());
			u.setPassword(user.getPassword());
		}
		else {
			throw new Exception("Not found");
		}
		
		userRepo.save(u);
		return u;
	}

}
