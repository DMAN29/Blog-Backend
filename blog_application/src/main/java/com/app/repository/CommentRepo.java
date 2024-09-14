package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Comment;

public interface CommentRepo extends JpaRepository<Comment, Long> {

}
