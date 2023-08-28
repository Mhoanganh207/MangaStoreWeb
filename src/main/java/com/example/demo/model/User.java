package com.example.demo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Setter;


@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Size(min = 6)
	@NotEmpty
	@Setter
	private String name;
	@Email
	@Setter
	private String email;
	@NotEmpty
	@Setter
	private String pass;
	
	
	public String getName() {
		return this.name;
	}
	public String getPass() {
		return this.pass;
	}

}
