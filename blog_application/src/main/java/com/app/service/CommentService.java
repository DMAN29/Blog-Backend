package com.app.service;

import java.util.List;

import com.app.exception.BlogException;
import com.app.exception.CommentException;
import com.app.exception.UserException;
import com.app.model.Comment;

public interface CommentService {

	Comment postComment(Comment comment, Long blogId, String jwt) throws UserException, BlogException;

	List<Comment> getCommentsByEmail(String email) throws UserException;
	
	List<Comment> getCommentsByBlog(Long id) throws BlogException;

	Comment editComment(Long id, String jwt, Comment comment) throws CommentException, UserException;

	String deleteComment(Long id, String jwt) throws CommentException, UserException;

}
