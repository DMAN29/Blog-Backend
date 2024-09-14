package com.app.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.exception.UserException;
import com.app.model.User;
import com.app.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authManager;
	
	private BCryptPasswordEncoder  encoder = new BCryptPasswordEncoder(12);
	
	public User register(User user)throws UserException {
		User isEmailExist =  userRepo.findByEmail(user.getEmail());
		if(isEmailExist!=null) {
			throw new UserException("Email is Already Used with another Account");
		}
		
		User createdUser = new User();
		createdUser.setFristName(user.getFristName());
		createdUser.setLastName(user.getLastName());
		createdUser.setEmail(user.getEmail());
		createdUser.setPassword(encoder.encode(user.getPassword()));
		createdUser.setPhoneNo(user.getPhoneNo());
		createdUser.setCreatedAt(LocalDateTime.now());
		createdUser.setUpdatedAt(LocalDateTime.now());
		
		return userRepo.save(createdUser);
		
	}

	public String verifyUser(User user) throws UserException{
		Authentication authentication  = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
		
		if(authentication.isAuthenticated()) {
			return jwtService.generateToken(user.getEmail());
		}
		throw new UserException("User with UserName and Password not found");
	}

	public User getUser(String jwt) throws UserException {
		User user = userRepo.findByEmail(jwtService.extractUserName(jwt));
		if(user==null) {
			throw new UserException("User with Email not found");
		}
		return user;
	}
	
	public User getUserByEmail(String email)throws UserException{
		User user =userRepo.findByEmail(email); 
		if(user==null) {
			throw new UserException("User with Email not found");
		}
		return user;
	}
	
	public User updateUser(User user) {
		user.setUpdatedAt(LocalDateTime.now());
		return userRepo.save(user);
	}
	
}
