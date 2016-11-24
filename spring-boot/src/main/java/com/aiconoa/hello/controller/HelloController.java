package com.aiconoa.hello.controller;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/hello")
public class HelloController {
	
	private static final Logger LOGGER = Logger.getLogger(HelloController.class.getName());
	
	@RequestMapping(value="/{user}", method=RequestMethod.GET)
	public String helloUser(@PathVariable(name="user") String user, Model model) {
		LOGGER.info(user);
		
		model.addAttribute("user", user);
		
		return "hello";
	}
	

}
