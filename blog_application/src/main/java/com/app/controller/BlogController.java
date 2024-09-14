package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.BlogException;
import com.app.exception.UserException;
import com.app.model.Blog;
import com.app.service.BlogService;

@RestController
@RequestMapping("/blogs")
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@GetMapping("")
	public ResponseEntity<List<Blog>> getBlogs(){
		return new ResponseEntity<List<Blog>>( blogService.getAllBlogs(),HttpStatus.OK);
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<List<Blog>> getBlogsByEmail(@RequestParam String email) throws UserException{
		return new ResponseEntity<List<Blog>>(blogService.getBlogsByEmail(email),HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<Blog> postBlog(@RequestBody Blog blog, @RequestHeader("Authentication") String jwt) throws UserException{
		return new ResponseEntity<Blog>(blogService.postBlog(blog,jwt),HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Blog> updateBlog(@RequestParam Long id,@RequestBody Blog blog,@RequestHeader("Authentication") String jwt) throws BlogException, UserException{
		return new ResponseEntity<Blog>(blogService.updateBlog(id,blog,jwt),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteBlog(@RequestParam Long id,@RequestHeader("Authenticaion")String jwt) throws BlogException, UserException{
		return new ResponseEntity<String>(blogService.deleteBlog(id,jwt),HttpStatus.OK);
	}
}
