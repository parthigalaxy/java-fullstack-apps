package com.skill.tracker.processor.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.skill.tracker.api.jaxb.types.SkillTrackerRequest;
import com.skill.tracker.api.jaxb.types.SkillTrackerResponse;

@FeignClient(value = "${feign.client.skill.tracker.api}", url = "${feign.client.url.skill.tracker.api}")
public interface SkillTrackerApiClient {
	
	@RequestMapping(method = RequestMethod.POST, value = "/engineer/add-profile")
	public SkillTrackerResponse addNewProfile(SkillTrackerRequest skillTrackerRequest);

}
