package com.skill.tracker.processor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.skill.tracker.processor.service.SkillAdminService;

@SpringBootTest
class SkillTrackerProcessorApplicationTests {

	@Autowired
	SkillAdminService skillAdminService;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void skillAdminServiceTest() {
//		skillAdminService.createDocument();
	}
	

}
