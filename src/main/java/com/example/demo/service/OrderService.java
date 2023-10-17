package com.example.demo.service;

import java.util.HashSet;

import java.util.Set;

import com.example.demo.Repository.OrderRepository;
import com.example.demo.model.Item;
import com.example.demo.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	private Set<Integer> orderList = new HashSet<Integer>();
	private Set<Item> booklist = new HashSet<>();


	
	
	public void Add(int id) {
		this.orderList.add(id);
	}
	
	public void Delete(String id) {
		this.orderList.removeIf(integer -> integer==Integer.parseInt(id));
		this.booklist.removeIf(item -> item.getProductId()==Integer.parseInt(id));
		
	}
	public void Clear() {
		this.orderList.clear();
		this.booklist.clear();
	}

	public void Save(Order order){
		this.orderRepository.save(order);

	}

}
