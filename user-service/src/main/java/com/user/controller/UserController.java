package com.user.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.user.bean.UserVo;
import com.user.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService service;

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("user")
	public void createUser(@Valid @RequestBody UserVo userVo) {
		System.out.println("Creating User");
		service.createUser(userVo);
		System.out.println("User Created");
	}

	@GetMapping({ "/v1/user/{id}", "/v1/user" })
	public List<UserVo> getUsers(@PathVariable(required = false) Optional<Integer> id) {
		System.out.println("getting Users");
		return id.isPresent() && id.get() > 0 ? service.getUser(Arrays.asList(id.get())) : service.getUsers();
	}

	@DeleteMapping("/v1/user/{id}")
	public void deleteUser(@PathVariable Integer id) {
		System.out.println("deleting Users");
		service.deleteUser(id);
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
