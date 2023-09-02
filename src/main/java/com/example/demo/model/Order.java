package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private User user;
	
	@ManyToMany
	@JoinTable(
	        name = "Order_Book", 
	        joinColumns = { @JoinColumn(name = "order_id") }, 
	        inverseJoinColumns = { @JoinColumn(name = "book_id") }
	    )
	private List<Book> book_list = new ArrayList<Book>();
	
	private String address;
	private String phonenumber;
	private double totalprice;

}
