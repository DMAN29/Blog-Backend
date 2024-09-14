package com.app.service;

import java.util.List;


import com.app.exception.BlogException;
import com.app.exception.UserException;
import com.app.model.Blog;

public interface BlogService {

	public List<Blog> getAllBlogs();

	public List<Blog> getBlogsByEmail(String email) throws UserException;
	
	public Blog findById(Long id) throws BlogException;

	public Blog postBlog(Blog blog, String jwt) throws UserException;

	public Blog updateBlog(Long id, Blog blog, String jwt) throws BlogException, UserException;

	public Blog updateBlog(Blog blog);
	
	public String deleteBlog(Long id, String jwt) throws BlogException,UserException;
}
