package com.sivatechie.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sivatechie.demo.model.User;
import com.sivatechie.demo.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping
	public User saveUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@GetMapping
	public List<User> getAllUsers() {
	    return userRepository.findAll();
	}
}
