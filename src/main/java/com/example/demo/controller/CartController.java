package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.example.demo.Repository.BookRepository;
import com.example.demo.model.Book;
import com.example.demo.service.OrderService;

@RestController
@SessionAttributes({"itemList"})
public class CartController {
    
	private BookRepository bookRepository;
	private OrderService orderService;

	public CartController(BookRepository bookRepository, OrderService orderService) {
		super();
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
	}
	
	@GetMapping("/booklist")
	public ResponseEntity<List<Integer>> getBookbyID(){
		
		for (int i:orderService.getOrderList()) {
			System.out.print(i+"");
		}
		System.out.println();
		
		return new ResponseEntity<List<Integer>>(orderService.getOrderList(), HttpStatus.OK);
	}
	
	@DeleteMapping("/book/{id}")
	public void DeleteId(@PathVariable String id) {
		
	}
	
	
	
	
}
