package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@SessionAttributes({"message","alertflag","signedin"})

public class AccountController {
	
	
	private int flag =0;
	
	
	@Autowired
	private UserService userService;
  
	public AccountController(UserService userService) {
		super();
		this.userService = userService;
	}
	@RequestMapping(value ="/signup", method = RequestMethod.GET)
	public String SignUpPage(Model model){
		model.addAttribute("alertflag", flag);
		
           return "signup";		
	}
	@RequestMapping("/signin")
	public String SignInPage(Model model){
		model.addAttribute("alertflag", flag);
           return "signin";		
	}
	
	@RequestMapping(value ="/signup", method = RequestMethod.POST)
	public RedirectView CreateAccount(Model model,@RequestParam String name,
			@RequestParam String email, @RequestParam String pass,
			@RequestParam String re_pass, @Valid User user, BindingResult result,RedirectAttributes redirectAttributes ) throws Exception {
		
		
		
			user.setName(name);
			user.setPass(pass);
			user.setEmail(email);
			
			if(result.hasErrors() || !(pass.equals(re_pass))) {
				flag=1;
				model.addAttribute("alertflag", flag);
				model.addAttribute("message", "Failed");
				
		
				return new RedirectView("/signin");
		}
		
		userService.Save(user);
		
		redirectAttributes.addFlashAttribute("signedin", "1");
    	
    	return new RedirectView("/index");
		
	}
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public RedirectView SignInAccount(Model model, @RequestParam String your_name, @RequestParam String your_pass,RedirectAttributes redirectAttributes) {
		
		boolean check =false;
		
		List<User> users = userService.findAll();
		
		Map<String,String> map = users.stream().collect(Collectors.toMap(User::getName, User::getPass));
		Set<String> set = map.keySet();
        for (Object key : set) {
            if(your_name.equals(key)) {
            	check = true;
            	break;
            }
        }
        if(your_pass.equals(map.get(your_name)) && check) {
        	
        	redirectAttributes.addFlashAttribute("signedin", "1");
        	
        	return new RedirectView("/index");
        }
        flag=1;
		model.addAttribute("alertflag", flag);
		model.addAttribute("message", "Failed");
		
		return new RedirectView("/signin");
		
	}
	
	@RequestMapping("/logout")
	public RedirectView LogOut(Model model,RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("signedin", "0");
		flag=0;
		
		return new RedirectView("/index");
		
		
		
	}
	@RequestMapping("/sublogout")
	public RedirectView ItemPageLogOut(Model model,RedirectAttributes redirectAttributes,HttpServletRequest request) {
		redirectAttributes.addFlashAttribute("signedin", "0");
		flag=0;
		
		 String referer = request.getHeader("Referer");
		return new RedirectView(referer);
		
		
		
	}
	
}
