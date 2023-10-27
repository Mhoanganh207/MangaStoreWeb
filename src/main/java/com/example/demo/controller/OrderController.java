package com.example.demo.controller;

import com.example.demo.model.Item;
import com.example.demo.model.Order;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class OrderController {

	private OrderService orderService;

	private UserService userService;

	public OrderController(OrderService orderService, UserService userService) {
		this.orderService = orderService;
		this.userService = userService;
	}

	@RequestMapping(value = "/payment",method = RequestMethod.GET)
	public String  CheckOut() {
		return "checkout";
	}
	@RequestMapping( value ="/payment", method = RequestMethod.POST)
	public RedirectView PostOrder(@RequestParam String username, @RequestParam String address, @RequestParam  String phonenumber) {
		double totalprice =0;
		for(Item item : orderService.getBooklist()) {
			totalprice += item.getPrice();
		}
		Order order = new Order();
		order.setAddress(address);
		order.setTotalprice(totalprice);
		order.setPhonenumber(phonenumber);
		order.setUser(userService.getId(username));
		orderService.getBooklist().forEach(item ->{
			order.getItems().add(item);
			item.setOrder(order);
		});

		orderService.Save(order);

		orderService.Clear();

		return new RedirectView("/payment");
	}
}
