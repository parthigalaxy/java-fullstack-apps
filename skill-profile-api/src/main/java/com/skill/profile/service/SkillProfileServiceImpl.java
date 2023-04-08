package com.skill.profile.service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skill.profile.api.jaxb.types.SkillProfileRequest;
import com.skill.profile.api.jaxb.types.SkillProfileResponse;
import com.skill.profile.api.jaxb.types.SkillProfileType;
import com.skill.profile.config.SkillProfileException;
import com.skill.profile.mapper.SkillProfileMapper;
import com.skill.profile.model.Skill;
import com.skill.profile.model.SkillProfile;
import com.skill.profile.repository.SkillProfileRepository;
import com.skill.profile.util.DateUtil;
import com.skill.profile.util.SkillProfileResponseHelper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SkillProfileServiceImpl implements SkillProfileService {

	@Autowired
	SkillProfileRepository skillProfileRepository;

	@Autowired
	SkillProfileMapper skillProfileMapper;

	@Override
	public SkillProfileResponse addNewProfile(SkillProfileRequest skillProfileRequest) {

		log.debug("addNewProfile >> ", skillProfileRequest);
		SkillProfileResponse skillProfileResponse = null;
		SkillProfile skillProfile = skillProfileMapper
				.skillProfileTypeToSkillProfile(skillProfileRequest.getSkillProfile());

		log.debug("skillProfile >> ", skillProfile);

		SkillProfile skillProfileRes = skillProfileRepository.save(skillProfile);
		skillProfileResponse = SkillProfileResponseHelper.createSkillProfileResponse("SUCCESS",
				"User added Successfully");
		skillProfileResponse.getSkillProfiles().add(skillProfileMapper.skillProfileToSkillProfileType(skillProfileRes));

		return skillProfileResponse;
	}

	@Override
	public SkillProfileResponse updateProfile(SkillProfileRequest skillProfileRequest) {

		log.debug("updateProfile >> ", skillProfileRequest);

		Optional<SkillProfile> skillProfileO = skillProfileRepository
				.findById(skillProfileRequest.getSkillProfile().getId());

		if (skillProfileO.isEmpty()) {
			throw new SkillProfileException("NO Data found for the ID provided Please check the ID");
		}

		SkillProfile skillProfile = skillProfileO.get();
		if (!DateUtil.isDateBefore(10, skillProfile.getUpdateTime())) {
			throw new SkillProfileException("Update of profile allowed only after 10 days of previse update");
		}

		updateSkills(skillProfile, skillProfileRequest.getSkillProfile());
		log.debug("skillProfile >> ", skillProfile);

		SkillProfile skillProfileRes = skillProfileRepository.save(skillProfile);

		SkillProfileResponse skillProfileResponse = SkillProfileResponseHelper.createSkillProfileResponse("SUCCESS",
				"User Updated Successfully");
		skillProfileResponse.getSkillProfiles().add(skillProfileMapper.skillProfileToSkillProfileType(skillProfileRes));
		return skillProfileResponse;
	}

	private void updateSkills(SkillProfile skillProfile, SkillProfileType skillProfileType) {

		skillLevelMapping(skillProfile.getSkillLevals(), skillProfileType.getSkillLevals());
		skillLevelMapping(skillProfile.getNonTechnicalSkillLevals(), skillProfileType.getNonTechnicalSkillLevals());
		skillProfile.setUpdateTime(Calendar.getInstance().getTime());

	}

	private void skillLevelMapping(Set<Skill> skillSet, List<com.skill.profile.api.jaxb.types.Skill> skillList) {
		for (Skill skill : skillSet) {
			for (com.skill.profile.api.jaxb.types.Skill skillType : skillList) {
				if (skillType.getSkill().equalsIgnoreCase(skill.getSkill())) {
					skill.setLevel(skillType.getLevel());
				}
			}
		}
	}

}
