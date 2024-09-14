package com.app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.BlogException;
import com.app.exception.CommentException;
import com.app.exception.UserException;
import com.app.model.Blog;
import com.app.model.Comment;
import com.app.model.User;
import com.app.repository.CommentRepo;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private UserService userService;

	@Autowired
	private BlogService blogService;

	@Override
	public Comment postComment(Comment comment, Long blogId, String jwt) throws UserException, BlogException {
		User user = userService.getUser(jwt);
		if (user == null) {
			throw new UserException("User not found");
		}

		Blog blog = blogService.findById(blogId);

		comment.setAuthor(user);

		comment.setBlog(blog);
		comment.setUpdatedAt(LocalDateTime.now());
		comment.setCreatedAt(LocalDateTime.now());

		Comment savedComment = commentRepo.save(comment);

		blog.getComments().add(comment);
		user.getComments().add(comment);

		return savedComment;
	}

	@Override
	public List<Comment> getCommentsByEmail(String email) throws UserException {
		User user = userService.getUserByEmail(email);
		return user.getComments();
	}

	@Override
	public List<Comment> getCommentsByBlog(Long id) throws BlogException {
		Blog blog = blogService.findById(id);
		return blog.getComments();
	}

	@Override
	public Comment editComment(Long id, String jwt, Comment comment) throws CommentException, UserException {
		User user = userService.getUser(jwt);

		Comment existingComment = findById(id);

		if (existingComment.getAuthor().getEmail().equals(user.getEmail())) {
			existingComment.setContent(comment.getContent());
			existingComment.setUpdatedAt(LocalDateTime.now());
			commentRepo.save(existingComment);
		}

		throw new CommentException("You dont have access to edit this comment");
	}

	@Override
	public String deleteComment(Long id,String jwt) throws CommentException, UserException {
		User user = userService.getUser(jwt);
		
		Comment comment = findById(id);
		
		Blog blog = comment.getBlog();
		
		if(comment.getAuthor().getEmail().equals(user.getEmail())|| comment.getBlog().getAuthor().getEmail().equals(user.getEmail())) {
			commentRepo.delete(comment);
			user.getComments().remove(comment);
			blog.getComments().remove(comment);
			userService.updateUser(user);
			blogService.updateBlog(blog);
			
			return "Comment Deleted successfully";
		}
		
		throw new CommentException("You dont have access to delete this comment");
	}

	public Comment findById(Long id) throws CommentException {
		Optional<Comment> comment = commentRepo.findById(id);
		if (comment.isEmpty()) {
			throw new CommentException("Comment not Found");
		}
		return comment.get();
	}

}
