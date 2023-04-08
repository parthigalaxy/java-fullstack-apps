package com.skill.tracker.processor.util;

import java.util.HashMap;
import java.util.Map;

public final class SkillLevalHelper {
	
	private SkillLevalHelper() {}
	
	static Map<String, String> SKILLLEVALMAP = new HashMap<>();
	
	static Map<String, String> NONTECHNICALSKILLLEVALMAP = new HashMap<>();
	
	static String TECH = "TECH";
	
	static {
		SKILLLEVALMAP.put("1","HTML-CSS-JAVASCRIPT");
		SKILLLEVALMAP.put("2","ANGULAR");
		SKILLLEVALMAP.put("3","REACT");
		SKILLLEVALMAP.put("4","SPRING");
		SKILLLEVALMAP.put("5","RESTFUL");
		SKILLLEVALMAP.put("6","HIBERNATE");
		SKILLLEVALMAP.put("7","GIT");
		SKILLLEVALMAP.put("8","DOCKER");
		SKILLLEVALMAP.put("9","JENKINS");
		SKILLLEVALMAP.put("10","AWS");
		
		NONTECHNICALSKILLLEVALMAP.put("1","SPOKEN");
		NONTECHNICALSKILLLEVALMAP.put("2","COMMUNICATION");
		NONTECHNICALSKILLLEVALMAP.put("3","APTITUDE");
	}
	
	public static String skillLevalsMap(String id, String type) {
		if(TECH.equals(type)) {
			return SKILLLEVALMAP.get(id);			
		}else {
			return NONTECHNICALSKILLLEVALMAP.get(id);
		}
	}
	
}
