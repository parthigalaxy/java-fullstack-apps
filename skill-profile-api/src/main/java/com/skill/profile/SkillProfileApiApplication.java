package com.skill.profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {
        SecurityAutoConfiguration.class,
        ManagementWebSecurityAutoConfiguration.class
})
@ComponentScan(basePackages={"com.skill.profile"})
public class SkillProfileApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkillProfileApiApplication.class, args);
	}

}
