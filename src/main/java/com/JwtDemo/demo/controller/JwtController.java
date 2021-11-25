package com.JwtDemo.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.JwtDemo.demo.dao.JwtRequest;
import com.JwtDemo.demo.dao.JwtResponse;
import com.JwtDemo.demo.helper.JwtUtil;
import com.JwtDemo.demo.service.CustomUserDetailService;

@RestController
public class JwtController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Autowired
	private JwtUtil jwtUtil;

	@RequestMapping(value="/token", method= RequestMethod.POST)
	public JwtResponse generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
	 System.out.println(jwtRequest);

	 try {
		 authenticationManager.authenticate
		 (new UsernamePasswordAuthenticationToken
				 (jwtRequest.getUsername(), jwtRequest.getPassword()));
		
	} catch (UsernameNotFoundException e) {
		 e.printStackTrace();
		 throw new Exception("Bad Credentials ");
		
	} catch(BadCredentialsException e) {
		e.printStackTrace();
		throw new Exception("Bad Credentials!!");
	}
	 UserDetails userDetails = customUserDetailService.loadUserByUsername(jwtRequest.getUsername());
	 String token = jwtUtil.generateToken(userDetails);
	 
	return new JwtResponse(token);
		
	}
}
