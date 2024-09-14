package com.app.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String email;
	
	private String password;
	
	private String fristName ;
	
	private String lastName;
	
	private Long phoneNo;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
	
	@OneToMany
	private	List<Blog> blog = new ArrayList<>();
	
	@OneToMany
	private List<Comment> comments = new ArrayList<>();
	
}
