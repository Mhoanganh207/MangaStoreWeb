package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.Repository.BookRepository;
import com.example.demo.Repository.OrderRepository;
import com.example.demo.model.Book;
import com.example.demo.model.Order;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;

@RestController
@SessionAttributes({})
public class CartController {
	
	@Autowired
	private UserService userService;
	private OrderRepository orderRepository;
	private BookRepository bookRepository;
	private OrderService orderService;

	

	
	

	public CartController(UserService userService, OrderRepository orderRepository, BookRepository bookRepository,
			OrderService orderService) {
		super();
		this.userService = userService;
		this.orderRepository = orderRepository;
		this.bookRepository = bookRepository;
		this.orderService = orderService;
	}

	@GetMapping("/book/{id}")
	public ResponseEntity<Book> getBookbyID(@PathVariable String id){
		Book book = bookRepository.findById(Integer.parseInt(id)).get();
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}
	
	@PostMapping("/book/id")
	public void postId(@RequestBody Book book,Model model){
		orderService.Add(book.getId());
		Book books = bookRepository.findById(book.getId()).get();
		orderService.getBooklist().add(books);
	}
	
	@GetMapping("/booklist")
	public ResponseEntity<List<Integer>> getBookbyID(){
		return new ResponseEntity<List<Integer>>(orderService.getOrderList(), HttpStatus.OK);
	}
	
	@DeleteMapping("/book/{id}")
	public void DeleteId(@PathVariable String id) {
		orderService.Delete(id);
		orderService.getBooklist().get(Integer.parseInt(id)).setId(-1);
		for(int i:orderService.getOrderList()) {
			if(i != -1) {
				return;
			}
		}
		orderService.Clear();
	}
	
	
	@GetMapping("/cartlist")
	public ResponseEntity<List<Book>> getBookList(){
		return new ResponseEntity<List<Book>>(orderService.getBooklist(), HttpStatus.OK);
	}
	
	
	@RequestMapping( value ="/payment", method = RequestMethod.POST)
	public RedirectView  PostOrder(@RequestParam String username, @RequestParam String address, @RequestParam  String phonenumber) {
		double totalprice =0;
		orderService.getBooklist().removeIf(book -> book.getId()==-1);
		for(Book book : orderService.getBooklist()) {
			totalprice += book.getPrice();
		}
		Order order = new Order();
		order.setAddress(address);
		order.setBook_list(orderService.getBooklist());
		order.setTotalprice(totalprice);
		order.setPhonenumber(phonenumber);
		order.setUser(userService.getId(username));
		
		orderRepository.save(order);
		
		orderService.Clear();
		
		return new RedirectView("/payment");
	}
	
	
	
	
	
}
