package com.skill.tracker.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/ping")
@Slf4j
public class PingResource {

	@GetMapping
	public String ping() {
		log.debug("ping SUCCESS");
		return "SUCCESS";
	}
	
}
