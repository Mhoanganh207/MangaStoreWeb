package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import com.example.demo.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import com.example.demo.Repository.BookRepository;
import com.example.demo.model.Book;
import com.example.demo.model.Order;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;

@RestController
public class CartController {
	
	@Autowired
	private UserService userService;
	private BookRepository bookRepository;
	private OrderService orderService;


	public CartController(UserService userService, BookRepository bookRepository, OrderService orderService) {
		this.userService = userService;
		this.bookRepository = bookRepository;
		this.orderService = orderService;

	}

	@GetMapping("/book/{id}")
	public ResponseEntity<Book> getBookbyID(@PathVariable String id){
		Book book = bookRepository.findById(Integer.parseInt(id)).get();
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}
	
	@PostMapping("/book/id")
	public void postId(@RequestBody Book book){
		orderService.Add(book.getId());
		System.out.println(book.getId());
		Book books = bookRepository.findById(book.getId()).get();
		Item item = new Item(books);
		for(Item i: orderService.getBooklist()){
			if(i.getProductId()==book.getId()){
				i.setQuantity(i.getQuantity()+1);
				return;
			}
		}
		item.setQuantity(1);
		orderService.getBooklist().add(item);

	}
	
	@GetMapping("/booklist")
	public ResponseEntity<Set<Integer>> getBookbyID(){
		return new ResponseEntity<Set<Integer>>(orderService.getOrderList(), HttpStatus.OK);
	}
	
	@DeleteMapping("/book/{id}")
	public void DeleteId(@PathVariable String id) {
		orderService.Delete(id);
		if(orderService.getOrderList().isEmpty()) {
			orderService.Clear();
		}

		System.out.println("deleted");
	}
	
	
	@GetMapping("/cartlist")
	public ResponseEntity<Set<Item>> getBookList(){
		return new ResponseEntity<Set<Item>>(orderService.getBooklist(), HttpStatus.OK);
	}
	
	


	@GetMapping("/item/add/{id}")
	public ResponseEntity<Map<Integer,Double>> AddQuantity(@PathVariable String id){
		int index = Integer.parseInt(id);
		int quantity = 0;
		double price = 0;
		for(Item item : orderService.getBooklist()){
			if(item.getProductId()==index){
				item.setQuantity(item.getQuantity()+1);
				quantity = item.getQuantity();
				price = item.getPrice();
				break;
			}
		}

		Map<Integer,Double>  map = new HashMap<>();
		map.put(quantity,price);

		return new ResponseEntity<Map<Integer,Double>>(map, HttpStatus.OK);

	}

	@GetMapping("/item/remove/{id}")
	public ResponseEntity<Map<Integer,Double>> removeQuantity(@PathVariable String id){
		int quantity=0;
		int index = Integer.parseInt(id);
		double price = 0;
		for(Item item : orderService.getBooklist()){
			if(item.getProductId()==index){
				item.setQuantity(item.getQuantity()-1);
				quantity=item.getQuantity();
				price = item.getPrice();
				if(quantity==0){
					orderService.getBooklist().remove(item);
				}
				break;
			}
		}

		Map<Integer,Double>  map = new HashMap<>();
		map.put(quantity,price);

		return new ResponseEntity<Map<Integer,Double>>(map, HttpStatus.OK);

	}
	
	
	
	
	
}
