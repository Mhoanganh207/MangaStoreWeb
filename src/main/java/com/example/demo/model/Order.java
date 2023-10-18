package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private User user;
	private String address;
	private String phonenumber;
	private double totalprice;


	@OneToMany(cascade = CascadeType.ALL)
	private Set<Item> items = new HashSet<Item>();

}
