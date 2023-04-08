package com.skill.tracker.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skill.tracker.server.model.User;
import com.skill.tracker.server.service.JwtGeneratorInterface;
import com.skill.tracker.server.service.UserService;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

	private UserService userService;
	private JwtGeneratorInterface jwtGenerator;

	@Autowired
	public UserController(UserService userService, JwtGeneratorInterface jwtGenerator) {
		this.userService = userService;
		this.jwtGenerator = jwtGenerator;
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User user) {
		try {
			if (user.getUserName() == null || user.getPassword() == null) {
				throw new UsernameNotFoundException("UserName or Password is Empty");
			}
			User userData = userService.getUserByNameAndPassword(user.getUserName(), user.getPassword());
			if (userData == null) {
				throw new UsernameNotFoundException("UserName or Password is Invalid");
			}
			return new ResponseEntity<>(jwtGenerator.generateToken(userData), HttpStatus.OK);
		} catch (UsernameNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

}
