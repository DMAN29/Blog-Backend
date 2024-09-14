package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Blog;
import com.app.model.User;

public interface BlogRepo extends JpaRepository<Blog, Long> {

	List<Blog> findByAuthor(User user);

}
