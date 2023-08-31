package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="book")
public class Book {
	
	@Id
	private int id;
	private String name;
	private String imageURL;
	private String author;
	private double preprice;
	private double price;
	private String category;
	@Column(name="description",length = 1000)
	private String description;
	@ManyToOne
	private Order order;

}
