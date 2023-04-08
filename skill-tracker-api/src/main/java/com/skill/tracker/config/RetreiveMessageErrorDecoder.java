package com.skill.tracker.config;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skill.profile.api.jaxb.types.SkillProfileResponse;
import com.skill.tracker.mapper.SkillTrackerProfileMapper;

import feign.Response;
import feign.codec.ErrorDecoder;

public class RetreiveMessageErrorDecoder implements ErrorDecoder {
	
	@Autowired
	SkillTrackerProfileMapper skillTrackerProfileMapper;

	private final ErrorDecoder errorDecoder = new Default();
	
	@Override
	public Exception decode(String methodKey, Response response) {
		
		SkillProfileResponse skillProfileResponse = null;
        try (InputStream bodyIs = response.body().asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            skillProfileResponse = mapper.readValue(bodyIs, SkillProfileResponse.class);
        } catch (IOException e) {
            return new Exception(e.getMessage());
        }
        
        switch (response.status()) {
	        case 400:
	            return new SkillTrackerException("Bad Request", skillTrackerProfileMapper.profileResponseToResponse(skillProfileResponse.getResponse()) );
	        default:
	        	return errorDecoder.decode(methodKey, response);
	    }

	}

	
}
