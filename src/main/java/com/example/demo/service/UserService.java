package com.example.demo.service;

import java.util.List;



import com.example.demo.model.User;

public interface UserService {
	
	
	void Save(User user);
	
	void Delete();
	
	List<User> findAll();
	
	User getId(String username);
	
	
	
	
	

}
