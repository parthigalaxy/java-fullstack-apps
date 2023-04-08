package com.skill.profile.util;

import com.skill.profile.api.jaxb.types.Messages;
import com.skill.profile.api.jaxb.types.ObjectFactory;
import com.skill.profile.api.jaxb.types.Response;
import com.skill.profile.api.jaxb.types.SkillProfileResponse;

public class SkillProfileResponseHelper {
	
	private static ObjectFactory objectFactory = new  ObjectFactory();
	private SkillProfileResponseHelper() {}
	
	public static SkillProfileResponse createSkillProfileResponse(String status, String text) {
		
		SkillProfileResponse skillProfileResponse = objectFactory.createSkillProfileResponse();
		Response response = objectFactory.createResponse();
		response.setStatus(status);
		response.setSatusText(text);
		skillProfileResponse.setResponse(response);
		return skillProfileResponse;
	}
	
	public static SkillProfileResponse createSkillProfileResponse(String status, String text, String type, String... messages) {
		
		SkillProfileResponse skillProfileResponse = createSkillProfileResponse(status, text);
		for(String message: messages) {
			skillProfileResponse.getResponse().getMessages().add(prepareMessage(type, message));
		}
		return skillProfileResponse;
	}
	
	private static Messages prepareMessage(String type, String message) {
		Messages messages = new Messages();
		messages.setType(type);
		messages.setMessage(message);
		return messages;
	}

}
