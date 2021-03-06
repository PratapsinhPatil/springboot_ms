package com.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

	@Autowired
	OrderService service;

	@Autowired
	private RestTemplate restTemplate;
	
	@PostMapping("order")
	@ResponseStatus(code = HttpStatus.CREATED)
	void createOrder(@Valid @RequestBody OrderVO order) {
		service.saveOrder(order);
		System.out.println("Item:" + order.getItem());
		System.out.println("Price:" + order.getPrice());
		ResponseEntity<String> responseEntity= restTemplate.getForEntity("http://EMAILSERVICE/v1/email", String.class);
		String emailServiceResponse=responseEntity.getBody();
		System.out.println("emailServiceResponse:" + emailServiceResponse);
		//Map<String, String> emails=new HashMap();
		
		ResponseEntity<String> responseEntity1= restTemplate.postForEntity("http://EMAILSERVICE/v1/email","pratapsinh.patil@gmail.com", String.class);
		System.out.println("emailServiceResponse for post request:" + responseEntity1.getBody());
	}

	@GetMapping("order")
	public List<OrderVO> getOrders() {

		return service.getOrders();

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleException(MethodArgumentNotValidException exct) {
		Map<String, String> errorMessages = new HashMap<>();
		exct.getAllErrors().forEach(error -> {
			String field = ((FieldError) error).getField();
			String message = ((FieldError) error).getDefaultMessage();
			errorMessages.put(field, message);
		});
		return errorMessages;
	}

}
