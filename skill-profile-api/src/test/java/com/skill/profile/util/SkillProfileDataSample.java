package com.skill.profile.util;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skill.profile.api.jaxb.types.Skill;
import com.skill.profile.api.jaxb.types.SkillProfileRequest;
import com.skill.profile.api.jaxb.types.SkillProfileType;

public class SkillProfileDataSample {
	
	@Test
	public void populateSkillProfileRequest() throws JsonProcessingException, DatatypeConfigurationException {
		
		SkillProfileRequest skillProfileRequest = new SkillProfileRequest();
		
		SkillProfileType skillProfileType = new SkillProfileType();
		skillProfileRequest.setSkillProfile(skillProfileType);
		
		skillProfileType.setId(UUID.randomUUID().toString());
		skillProfileType.setName("Varun");
		skillProfileType.setAssociateId("A123456");
		skillProfileType.setMobile("9867543210");
		skillProfileType.setEmail("varun@gmail.com");
		skillProfileType.getSkillLevals().add(populateSkill("HTML",2));
		skillProfileType.getSkillLevals().add(populateSkill("CSS",3));
		skillProfileType.getSkillLevals().add(populateSkill("JS",4));
		skillProfileType.getSkillLevals().add(populateSkill("SQL",3));
		skillProfileType.getNonTechnicalSkillLevals().add(populateSkill("SPOKEN",3));
		skillProfileType.getNonTechnicalSkillLevals().add(populateSkill("COMMUNICATION",2));
		skillProfileType.getNonTechnicalSkillLevals().add(populateSkill("APTITUDE",4));
		skillProfileType.setUpdateTime(populateXMLGregorianCalendar());
		skillProfileType.setUpdatedBy("Test");
		
		ObjectMapper objectMapper = new ObjectMapper();
		String output = objectMapper.writeValueAsString(skillProfileRequest);
		
		System.out.println(output);
		
	}
	
	public Skill populateSkill(String skillName, int level) {
		Skill skill = new Skill();
		skill.setLevel(level);
		skill.setSkill(skillName);
		return skill;
	}
	
	public XMLGregorianCalendar populateXMLGregorianCalendar() throws DatatypeConfigurationException {
		
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(Calendar.getInstance().getTime());
		return  DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
	}

}
