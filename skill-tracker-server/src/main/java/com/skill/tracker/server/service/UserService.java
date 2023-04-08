package com.skill.tracker.server.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.skill.tracker.server.model.User;

public interface UserService {
	
	public User getUserByNameAndPassword(String name, String password) throws UsernameNotFoundException;
	
}
