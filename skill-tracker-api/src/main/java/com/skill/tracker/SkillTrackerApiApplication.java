package com.skill.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {
        SecurityAutoConfiguration.class,
        ManagementWebSecurityAutoConfiguration.class
})
@EnableFeignClients
@ComponentScan(basePackages={"com.skill.tracker"})
public class SkillTrackerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkillTrackerApiApplication.class, args);
	}

}
