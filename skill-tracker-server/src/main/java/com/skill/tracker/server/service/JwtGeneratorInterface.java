package com.skill.tracker.server.service;

import java.util.Map;

import com.skill.tracker.server.model.User;

public interface JwtGeneratorInterface {
	
	Map<String, String> generateToken(User user);

}
