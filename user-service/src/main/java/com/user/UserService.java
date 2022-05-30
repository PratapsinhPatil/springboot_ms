package com.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	UserRepository repository;

	public void createUser(UserVo userVo) {
		repository.save(userVo);
	}

	public List<UserVo> getUsers() {
		return repository.findAll();
	}

	public List<UserVo> getUser(List<Integer> id) {
		return repository.findAllById(id);
	}

	public void deleteUser(Integer id) {
		repository.deleteById(id);
	}
}
