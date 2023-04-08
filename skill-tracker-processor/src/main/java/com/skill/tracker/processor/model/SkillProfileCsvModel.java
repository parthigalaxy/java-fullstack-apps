package com.skill.tracker.processor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SkillProfileCsvModel {

    protected String id;
    protected String name;
    protected String associateId;
    protected String mobile;
    protected String email;
    protected String skillLevals;
    protected String nonTechnicalSkillLevals;
    protected String updateTime;
    protected String updatedBy;
	
}
