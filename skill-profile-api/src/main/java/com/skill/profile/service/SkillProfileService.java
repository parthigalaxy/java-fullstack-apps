package com.skill.profile.service;

import com.skill.profile.api.jaxb.types.SkillProfileRequest;
import com.skill.profile.api.jaxb.types.SkillProfileResponse;

public interface SkillProfileService {
	
	SkillProfileResponse addNewProfile(SkillProfileRequest skillProfileRequest);
	
	SkillProfileResponse updateProfile(SkillProfileRequest skillProfileRequest);

}
