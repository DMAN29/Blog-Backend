package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.UserException;
import com.app.model.User;
import com.app.service.UserService;

@RestController
@RequestMapping("/profile")
public class UserControler {

	@Autowired
	private UserService userService;
	
	
	@GetMapping()
	public ResponseEntity<User> getUser(@RequestHeader("Authorization") String jwt) throws UserException{
		User user = userService.getUser(jwt);
		System.out.print("hello");
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
}
