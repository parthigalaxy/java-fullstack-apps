package com.skill.profile.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.core.convert.CassandraConverter;
import org.springframework.data.cassandra.core.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.core.mapping.SimpleUserTypeResolver;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import com.datastax.oss.driver.api.core.CqlSession;

@Configuration
@EnableCassandraRepositories(basePackages = "com.skill.profile.repository")
public class CassandraConfig {

//	@Bean
//    public DriverConfigLoaderBuilderCustomizer defaultProfile(){
//		System.setProperty("datastax-java-driver.basic.request.timeout", "30 seconds");
//        return builder -> builder.withString(DefaultDriverOption.METADATA_SCHEMA_REQUEST_TIMEOUT, "30 seconds").build();
//    }

//	@Bean
//	public CassandraMappingContext mappingContext(CqlSession cqlSession) {
//
//		CassandraMappingContext mappingContext = new CassandraMappingContext();
//		mappingContext.setUserTypeResolver(new SimpleUserTypeResolver(cqlSession));
//
//		return mappingContext;
//	}
//
//	@Bean
//	public CassandraConverter converter(CassandraMappingContext mappingContext) {
//		return new MappingCassandraConverter(mappingContext);
//	}

}
