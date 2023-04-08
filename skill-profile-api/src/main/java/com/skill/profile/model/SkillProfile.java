package com.skill.profile.model;

import java.util.Date;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Frozen;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Data
@Table
public class SkillProfile {

	@PrimaryKeyColumn(
		      name = "id", 
		      ordinal = 2, 
		      type = PrimaryKeyType.PARTITIONED, 
		      ordering = Ordering.DESCENDING)
	@Id
    protected String id;
	@Column
    protected String name;
	@Column
    protected String associateId;
	@Column
    protected String mobile;
	@Column
    protected String email;
	@Frozen
	@Column
	@CassandraType(type = CassandraType.Name.SET, typeArguments = CassandraType.Name.UDT, userTypeName = "skill")
    protected Set<Skill> skillLevals;
	@Frozen
	@Column
	@CassandraType(type = CassandraType.Name.SET, typeArguments = CassandraType.Name.UDT, userTypeName = "skill")
    protected Set<@Frozen Skill> nonTechnicalSkillLevals;
	@Column
	@CassandraType(type = CassandraType.Name.TIMESTAMP)
	protected Date updateTime;
	@Column
    protected String updatedBy;
    
}
