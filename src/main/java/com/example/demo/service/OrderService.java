package com.example.demo.service;

import com.example.demo.model.Item;
import com.example.demo.model.Order;

import java.util.Map;
import java.util.Set;


public interface OrderService {




	
	void Add(int id) ;
	
	void Delete(String id) ;
	void Clear() ;

	void Save(Order order);


	Set<Item> getBooklist();

    Set<Integer> getOrderList();
}
