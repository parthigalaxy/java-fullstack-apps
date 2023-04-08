package com.skill.tracker.util;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skill.tracker.api.jaxb.types.Skill;
import com.skill.tracker.api.jaxb.types.SkillTrackerRequest;
import com.skill.tracker.api.jaxb.types.SkillTrackerType;

public class SkillTrackerTypeDataSample {

	@Test
	public void populateSkillTrackerTypeRequest() throws JsonProcessingException, DatatypeConfigurationException {
		
		SkillTrackerRequest skillTrackerRequest = new SkillTrackerRequest();
		
		SkillTrackerType skillTrackerType = new SkillTrackerType();
		skillTrackerRequest.setSkillTracker(skillTrackerType);
		
		skillTrackerType.setId(UUID.randomUUID().toString());
		skillTrackerType.setName("Varun");
		skillTrackerType.setAssociateId("A123456");
		skillTrackerType.setMobile("9867543210");
		skillTrackerType.setEmail("varun@gmail.com");
		skillTrackerType.getSkillLevals().add(populateSkill("HTML",2));
		skillTrackerType.getSkillLevals().add(populateSkill("CSS",3));
		skillTrackerType.getSkillLevals().add(populateSkill("JS",4));
		skillTrackerType.getSkillLevals().add(populateSkill("SQL",3));
		skillTrackerType.getNonTechnicalSkillLevals().add(populateSkill("SPOKEN",3));
		skillTrackerType.getNonTechnicalSkillLevals().add(populateSkill("COMMUNICATION",2));
		skillTrackerType.getNonTechnicalSkillLevals().add(populateSkill("APTITUDE",4));
		skillTrackerType.setUpdateTime(populateXMLGregorianCalendar());
		skillTrackerType.setUpdatedBy("Test");
		
		ObjectMapper objectMapper = new ObjectMapper();
		String output = objectMapper.writeValueAsString(skillTrackerRequest);
		
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
