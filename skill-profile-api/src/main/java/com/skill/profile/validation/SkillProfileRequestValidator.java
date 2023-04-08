package com.skill.profile.validation;

import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.skill.profile.api.jaxb.types.Skill;
import com.skill.profile.api.jaxb.types.SkillProfileRequest;
import com.skill.profile.api.jaxb.types.SkillProfileType;
import com.skill.profile.config.SkillProfileException;

public class SkillProfileRequestValidator implements Validator {
	
	private static String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
			+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	
	private static String monileRegexPattern = "^\\d{10}$";

	@Override
	public boolean supports(Class<?> clazz) {
		return SkillProfileRequest.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmpty(errors, "skillProfile", "skillProfile.empty");

		if (!errors.hasErrors()) {

			SkillProfileRequest skillProfileRequest = (SkillProfileRequest) target;
			SkillProfileType skillProfileType = skillProfileRequest.getSkillProfile();
			skillConstraints(skillProfileType.getSkillLevals(), errors, "skillLevals");
			skillConstraints(skillProfileType.getNonTechnicalSkillLevals(), errors, "nonTechnicalSkillLevals");

			if (StringUtils.isEmpty(skillProfileType.getName())) {
				errors.rejectValue("skillProfile.name", "Name Should not Empty");
			} else {
				var nameLength = skillProfileType.getName().length();
				Integer min = 5, max = 30;
				if (nameLength < min && nameLength > max) {
					errors.rejectValue("skillProfile.name", "Name length should be between 5 to 30");
				}
			}

			if (StringUtils.isEmpty(skillProfileType.getAssociateId())) {
				errors.rejectValue("skillProfile.associateId", "AssociateId Should not Empty");
			} else {
				var nameLength = skillProfileType.getAssociateId().length();
				Integer min = 5, max = 30;
				if (nameLength < min && nameLength > max) {
					errors.rejectValue("skillProfile.associateId", "AssociateId length should be between 5 to 30");
				}
				if (!skillProfileType.getAssociateId().startsWith("CTS")) {
					errors.rejectValue("skillProfile.associateId", "AssociateId should Start with CTS");
				}
			}

			if (StringUtils.isEmpty(skillProfileType.getEmail())) {
				errors.rejectValue("skillProfile.email", "Email Should not Empty");
			} else if(!Pattern.compile(regexPattern).matcher(skillProfileType.getEmail()).matches()) {
				errors.rejectValue("skillProfile.email", "Email not valid");
			}

			if (StringUtils.isEmpty(skillProfileType.getMobile())) {
				errors.rejectValue("skillProfile.mobile", "Mobile Number Should not Empty");
			} else if(!Pattern.compile(monileRegexPattern).matcher(skillProfileType.getMobile()).matches()) {
				errors.rejectValue("skillProfile.mobile", "Mobile Number not valid");
			}
			
		}

	}

	private void skillConstraints(List<Skill> skillLevals, Errors errors, String skillType)
			throws SkillProfileException {
		Integer min = 0, max = 20;

		if (ObjectUtils.isNotEmpty(skillLevals)) {

			boolean isMinMax = skillLevals.stream()
					.filter(sl -> (null == sl.getLevel()) || !(sl.getLevel() >= min && sl.getLevel() <= max))
					.findFirst().isEmpty();
			if (!isMinMax) {
				errors.rejectValue("skillProfile." + skillType, skillType + " Should be between 0 to 20");
				throw new SkillProfileException(
						"skillProfile." + skillType + " : " + skillType + " Should be between 0 to 20");
			}

		} else {
			errors.rejectValue(skillType, skillType + " Should not Empty");
		}

	}

}
