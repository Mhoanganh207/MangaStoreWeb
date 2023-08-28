package com.example.demo.serviceimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.UserRepository;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	public UserRepository userRepository;

	@Override
	public void Save(User user) {
		// TODO Auto-generated method stub
		userRepository.save(user);
	}

	@Override
	public void Delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
	return (List<User>) userRepository.findAll();
	}

}
