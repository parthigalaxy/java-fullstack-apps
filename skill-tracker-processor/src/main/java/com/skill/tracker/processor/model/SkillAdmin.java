package com.skill.tracker.processor.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(indexName = "skill-block-3",createIndex = false)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SkillAdmin {

	@Id
	protected String id;
	@Field
    protected String name;
	@Field
	protected String associateId;
	@Field
	protected String mobile;
	@Field
	protected String email;
	@Field(type = FieldType.Nested, includeInParent = true)
    protected List<SkillLevel> skillLevals;
	@Field(type = FieldType.Nested, includeInParent = true)
    protected List<SkillLevel> nonTechnicalSkillLevals;
    @Field
    protected String updateTime;
    @Field
    protected String updatedBy;
	
}
