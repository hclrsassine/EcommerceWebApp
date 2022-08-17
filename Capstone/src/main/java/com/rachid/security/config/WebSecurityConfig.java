package com.rachid.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.rachid.services.GroupUserDetailsService;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	private final GroupUserDetailsService groupUserDetailsService;
	private final BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeRequests().antMatchers("/registration/**")
		.permitAll().anyRequest().authenticated().and().formLogin();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.authenticationProvider(daoAuthenticationProvider());
	}
	
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(bcryptPasswordEncoder);
		provider.setUserDetailsService(groupUserDetailsService);
		
		return provider; 
	}
}
