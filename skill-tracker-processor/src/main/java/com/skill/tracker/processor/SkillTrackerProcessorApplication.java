package com.skill.tracker.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@EnableAutoConfiguration(exclude = {
//        SecurityAutoConfiguration.class
        ManagementWebSecurityAutoConfiguration.class
})
@ComponentScan(basePackages={"com.skill.tracker.processor"})
public class SkillTrackerProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkillTrackerProcessorApplication.class, args);
	}

}
