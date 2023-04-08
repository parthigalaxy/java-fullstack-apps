package com.skill.tracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.codec.ErrorDecoder;

@Configuration
public class FeignSupportConfig {

	@Bean
	public ErrorDecoder errorDecoder() {
		return new RetreiveMessageErrorDecoder();
	}
	
}
