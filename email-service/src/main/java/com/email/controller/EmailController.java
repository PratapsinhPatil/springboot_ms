package com.email.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/v1/email")
public class EmailController {

@GetMapping
public String sendEmail() {
	
	System.out.println("Mail sent successfully");
	return "Mail sent successfully"; 
}

@PostMapping //("post")
public String sendEmail(@RequestBody String email) {
	
	System.out.println("Mail sent successfully to emailId:"+email);
	return "Mail sent successfully to emailId:"+email; 
}
	
}
