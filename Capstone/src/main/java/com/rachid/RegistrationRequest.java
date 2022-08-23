package com.rachid;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class RegistrationRequest {
	private final String firstName;
	private final String lastName;
	private final String username;
	private final String email;
	private final String password;
}