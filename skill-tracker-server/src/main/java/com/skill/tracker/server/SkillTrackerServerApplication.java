package com.skill.tracker.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SkillTrackerServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkillTrackerServerApplication.class, args);
	}

	@Bean
    public RestTemplate getRestTemplate() {
      return new RestTemplate();
    }
}
