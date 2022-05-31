package com.product;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/v1/product")
@RestController
public class ProductController {

	@Autowired
	private RestTemplate restTemplate;
	
	
	@Autowired
	ProductService service;

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("")
	public void create(@Valid @RequestBody ProductVO productVo) {
		System.out.println("Saving Product");
		service.saveProduct(productVo);
		System.out.println("Product saved");
		
		ResponseEntity<String> responseEntity= restTemplate.getForEntity("http://EMAILSERVICE/v1/email", String.class);
		String emailServiceResponse=responseEntity.getBody();
		System.out.println("emailServiceResponse:" + emailServiceResponse);
		//Map<String, String> emails=new HashMap();
		
		ResponseEntity<String> responseEntity1= restTemplate.postForEntity("http://EMAILSERVICE/v1/email","pratapsinh.patil@gmail.com", String.class);
		System.out.println("emailServiceResponse for post request:" + responseEntity1.getBody());
	}

	@GetMapping({ "/{id}", "" })
	public List<ProductVO> get(@PathVariable(required = false) Optional<Integer> id) {
		System.out.println("getting Users");
		return id.isPresent() && id.get() > 0 ? service.getProduct(Arrays.asList(id.get())) : service.getProducts();
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Integer id) {
		System.out.println("deleting Users");
		service.deleteProduct(id);
		System.out.println("deleted Users");

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
