package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Repository.OrderRepository;

@Controller
public class OrderController {
	public OrderRepository orderRepository;
	
	public OrderController(OrderRepository orderRepository) {
		super();
		this.orderRepository = orderRepository;
	}

	@RequestMapping("/payment")
	public String  CheckOut() {
		return "checkout";
	}
}
