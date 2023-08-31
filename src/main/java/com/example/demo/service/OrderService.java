package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Service
public class OrderService {
	
	private List<Integer> orderList = new ArrayList<Integer>();
	
	
	public void Add(int id) {
		this.orderList.add(id);
	}
	
	

}
