package com.app.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String title;
	
	private String content;
	
	@ManyToOne
	@JoinColumn(name="user_id",nullable=false)
	private User author;
	
	@JsonIgnore
	@OneToMany(mappedBy = "blog",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Comment> comments = new ArrayList<>();
	
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
