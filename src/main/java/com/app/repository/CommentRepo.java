package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Comment;
import com.app.model.User;

public interface CommentRepo extends JpaRepository<Comment, Long> {

	List<Comment> findByAuthor(User user);

}
