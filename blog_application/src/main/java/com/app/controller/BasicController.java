package com.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

	@GetMapping("/hello")
	public String  greeting() {
		return "Hello from Blog Website";
	}
}
