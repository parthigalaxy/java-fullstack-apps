package com.skill.tracker.server.repositery;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class JwtKidRepositeryImpl implements JwtKidRepositery{

	@Autowired
	RestTemplate restTemplate;
	
	@Value("${kong.jwt.url}")
	private String kongUrl;
	
	@Override
	public String retrieveKid() {
		
		@SuppressWarnings("unchecked")
		Map<String,Object> response = restTemplate.getForObject(kongUrl, Map.class);
		String key = "";
		if(null != response && null != response.get("data")) {
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> resp = (List<Map<String, Object>>) response.get("data");
			log.info("resp >> ", resp);
			key = (String) resp.get(0).get("key");
		}
		
		log.info("Response >> ", response, " key >>", key);
		
		return key;
	}

}
