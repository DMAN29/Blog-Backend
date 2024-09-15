package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.BlogException;
import com.app.exception.CommentException;
import com.app.exception.UserException;
import com.app.model.Comment;
import com.app.service.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {
	
	@Autowired
	private CommentService commentService;

	@PostMapping("/blog/{blogId}")
	public ResponseEntity<Comment> postComment(@RequestBody Comment comment,@PathVariable Long blogId,@RequestHeader("Authorization") String jwt) throws UserException, BlogException{
		return new ResponseEntity<Comment>(commentService.postComment(comment,blogId,jwt),HttpStatus.CREATED);
	}
	
	@GetMapping("/find")
	public ResponseEntity<List<Comment>> getCommentsByEmail(@RequestParam String email) throws UserException{
		return new ResponseEntity<List<Comment>>(commentService.getCommentsByEmail(email),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<Comment>> getCommentsByPost(@PathVariable Long id) throws BlogException{
		return new ResponseEntity<List<Comment>>(commentService.getCommentsByBlog(id),HttpStatus.OK);
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<Comment> editComment(@PathVariable Long id,@RequestBody Comment comment, @RequestHeader("Authorization")String jwt) throws CommentException, UserException{
		return new ResponseEntity<Comment>(commentService.editComment(id,jwt,comment),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteComment(@PathVariable Long id,@RequestHeader("Authorization") String jwt) throws CommentException, UserException{
		return new ResponseEntity<String>(commentService.deleteComment(id,jwt),HttpStatus.OK);
	}
}
