package com.skill.tracker.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.skill.profile.api.jaxb.types.SkillProfileRequest;
import com.skill.profile.api.jaxb.types.SkillProfileResponse;

@FeignClient(value = "${feign.client.skill.profile.api}", url = "${feign.client.url.skill.profile.api}")
public interface SkillProfileApiClient {

	@RequestMapping(method = RequestMethod.POST, value = "/profile/add")
	public SkillProfileResponse addProfile(SkillProfileRequest skillProfileRequest);
	
	@RequestMapping(method = RequestMethod.POST, value = "/profile/update")
	public SkillProfileResponse updateProfile(SkillProfileRequest skillProfileRequest);
	
}
