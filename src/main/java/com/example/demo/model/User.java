package com.example.demo.model;


import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Entity
@Data
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Size(min = 6)
	@NotEmpty
	
	private String name;
	@Email
	
	private String email;
	@NotEmpty
	
	private String pass;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
	private Set<Order> order_list = new HashSet<Order>();
	
	

}
