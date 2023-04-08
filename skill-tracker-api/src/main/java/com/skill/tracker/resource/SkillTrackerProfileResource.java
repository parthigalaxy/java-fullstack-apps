package com.skill.tracker.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skill.tracker.api.jaxb.types.SkillTrackerRequest;
import com.skill.tracker.api.jaxb.types.SkillTrackerResponse;
import com.skill.tracker.service.SkillProfileService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/engineer")
@Slf4j
public class SkillTrackerProfileResource extends BaseResource{
	
	@Autowired
	SkillProfileService skillProfileService;
	
	@PostMapping("add-profile")
	public ResponseEntity<SkillTrackerResponse> addProfile(@RequestBody SkillTrackerRequest skillTrackerRequest) {
		log.info("addProfile - start");
		
		SkillTrackerResponse skillTrackerResponse = skillProfileService.addNewProfile(skillTrackerRequest);
		
		log.info("addProfile - end");
		return resolveResponse(skillTrackerResponse, HttpStatus.OK);
	}
	
	@PostMapping("update-profile")
	public ResponseEntity<SkillTrackerResponse> updateProfile(@RequestBody SkillTrackerRequest skillTrackerRequest) {
		log.debug("updateProfile - start");
		
		SkillTrackerResponse skillTrackerResponse = skillProfileService.updateProfile(skillTrackerRequest);
		
		log.info("updateProfile - end");
		return resolveResponse(skillTrackerResponse, HttpStatus.OK);
	}
	

}
