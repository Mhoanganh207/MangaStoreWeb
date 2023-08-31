package com.example.demo.controller;

import java.text.DecimalFormat;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.Repository.BookRepository;
import com.example.demo.model.Book;

@Controller
@SessionAttributes({"hello","signedin"})
public class BookController {
	
	private BookRepository bookRepository;
   
	public BookController(BookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}
	@RequestMapping("/index")
	public String itempage() {
		
		return "/index";
	}
	@RequestMapping("/item/{id}")
	public String testpage(Model model, @PathVariable String id) {
		Optional<Book> bookopt = bookRepository.findById(Integer.parseInt(id));
		Book book = bookopt.get();
		model.addAttribute("id", book.getId());
		model.addAttribute("name", book.getName());
		model.addAttribute("category", book.getCategory());
		model.addAttribute("author",book.getAuthor());
		model.addAttribute("description", book.getDescription());
		DecimalFormat f = new DecimalFormat("##.00");
		model.addAttribute("preprice", f.format(book.getPreprice()));
		model.addAttribute("price", f.format(book.getPrice()));
		model.addAttribute("image", book.getImageURL());
		return "itempage/item1";
	}
}
