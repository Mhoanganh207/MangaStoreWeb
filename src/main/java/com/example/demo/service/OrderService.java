package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Service
public class OrderService {
	
	private List<Integer> orderList = new ArrayList<Integer>();
	private List<Book> booklist = new ArrayList<Book>();
	
	
	public void Add(int id) {
		this.orderList.add(id);
	}
	
	public void Delete(String id) {
		orderList.set(Integer.parseInt(id), -1);
		
	}
	public void Clear() {
		this.orderList.clear();
		this.booklist.clear();
	}

}
