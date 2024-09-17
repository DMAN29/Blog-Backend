package com.app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.BlogException;
import com.app.exception.UserException;
import com.app.model.Blog;
import com.app.model.User;
import com.app.repository.BlogRepo;

@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogRepo blogRepo;

	@Autowired
	private UserService userService;

	@Override
	public List<Blog> getAllBlogs() {
		return blogRepo.findAll();
	}

	@Override
	public List<Blog> getBlogsByEmail(String email) throws UserException {
		User user = userService.getUserByEmail(email);
		return blogRepo.findByAuthor(user);
	}

	@Override
	public Blog postBlog(Blog blog, String jwt) throws UserException {
		LocalDateTime time = LocalDateTime.now();
		User user = userService.getUser(jwt);
		blog.setAuthor(user);
		blog.setCreatedAt(time);
		blog.setUpdatedAt(time);

		Blog savedBlog = blogRepo.save(blog);
		user.getBlog().add(savedBlog);
		userService.updateUser(user);
		return savedBlog;
	}

	@Override
	public Blog updateBlog(Long id, Blog blog,String jwt) throws BlogException, UserException {
		User user = userService.getUser(jwt);
		
		Blog existingBlog = findById(id);
		
		if(user.getEmail().equals(existingBlog.getAuthor().getEmail())) {			
			existingBlog.setContent(blog.getContent());
			existingBlog.setTitle(blog.getTitle());
			existingBlog.setUpdatedAt(LocalDateTime.now());
			
			Blog updatedBlog = blogRepo.save(existingBlog);
			userService.updateUser(existingBlog.getAuthor());
			
			return updatedBlog;
		}
		throw new UserException("You are not allowed to update the blog");
	}

	@Override
	public String deleteBlog(Long id,String jwt) throws BlogException,UserException{
		
		User user = userService.getUser(jwt);
		
		Blog blog = findById(id);

		if(user.getEmail().equals(blog.getAuthor().getEmail())) {
			
			user.getBlog().remove(blog); 
			
			userService.updateUser(user);
			
			blogRepo.delete(blog);
			
			return "Blog deleted successfully";
		}
		throw new UserException("You are not allowed to delete the blog");
	}

	@Override
	public Blog findById(Long id) throws BlogException {
		Optional<Blog> isBlog = blogRepo.findById(id);
		if (isBlog.isEmpty()) {
			throw new BlogException("Blog not found with given id");
		}
		return isBlog.get();
	}
	
	@Override
	public Blog updateBlog(Blog blog) {
		return blogRepo.save(blog);
	}

}
