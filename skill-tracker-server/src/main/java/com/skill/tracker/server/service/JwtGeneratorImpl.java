package com.skill.tracker.server.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.skill.tracker.server.model.User;
import com.skill.tracker.server.repositery.JwtKidRepositery;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtGeneratorImpl implements JwtGeneratorInterface {

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${app.jwttoken.message}")
	private String message;
	
	@Autowired
	private JwtKidRepositery jwtKidRepositery;

	@Override
	public Map<String, String> generateToken(User user) {
		String jwtToken = "";
		Map<String, Object> jwdHeader= new HashMap<>();
		jwdHeader.put("kid", jwtKidRepositery.retrieveKid());
		jwdHeader.put("typ", "JWT");
		
		LocalDate daysAfter = LocalDate.now(ZoneId.systemDefault());
		daysAfter = daysAfter.plusDays(1);
		
		jwtToken = Jwts.builder().setHeader(jwdHeader)
				.setSubject(user.getUserName())
				.claim("role", user.getRole())
				.setIssuedAt(new Date())
				.setExpiration(Date.from(daysAfter.atStartOfDay(ZoneId.systemDefault()).toInstant()))
				.signWith(SignatureAlgorithm.HS256, secret).compact();
		
		Map<String, String> jwtTokenGen = new HashMap<>();
		jwtTokenGen.put("token", jwtToken);
		jwtTokenGen.put("message", message);
		return jwtTokenGen;
	}
}
