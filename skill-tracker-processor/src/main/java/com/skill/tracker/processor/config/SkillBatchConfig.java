package com.skill.tracker.processor.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.skill.tracker.api.jaxb.types.SkillTrackerType;
import com.skill.tracker.processor.model.SkillProfileCsvModel;

@Configuration
@EnableBatchProcessing
public class SkillBatchConfig {

	@Bean
	public Job job(JobRepository jobRepository,PlatformTransactionManager transactionManager,
			ItemReader<SkillProfileCsvModel> itemReader,
			ItemProcessor<SkillProfileCsvModel,SkillTrackerType> itemProcessor,
			ItemWriter<SkillTrackerType> itemWriter) {
		
		Step step = new StepBuilder("skill-csv-load", jobRepository)
				.<SkillProfileCsvModel,SkillTrackerType>chunk(100, transactionManager)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemWriter).build();
		
		return new JobBuilder("skill-profile-init", jobRepository)
				.preventRestart()
				.start(step).build();
	}
	
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
				.addScript("/org/springframework/batch/core/schema-hsqldb.sql")
				.generateUniqueName(true).build();
	}

	@Bean
	public JdbcTransactionManager transactionManager(DataSource dataSource) {
		return new JdbcTransactionManager(dataSource);
	}

	
	@Bean
	public FlatFileItemReader<SkillProfileCsvModel> itemReader(@Value("${csv.input.file}") Resource resource){
		
		FlatFileItemReader<SkillProfileCsvModel> flatFileItemReader = new FlatFileItemReader<SkillProfileCsvModel>();
		flatFileItemReader.setStrict(false);
		flatFileItemReader.setResource(resource);
		flatFileItemReader.setName("Skill-Profile-Csv-Reader");
		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(lineMapper());
		return flatFileItemReader;
	}

	private LineMapper<SkillProfileCsvModel> lineMapper() {
		
		DefaultLineMapper<SkillProfileCsvModel> defaultLineMapper = new DefaultLineMapper<SkillProfileCsvModel>();
		
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames("id","name","associateId","mobile","email","skillLevals","nonTechnicalSkillLevals","updateTime","updatedBy");
		
		BeanWrapperFieldSetMapper<SkillProfileCsvModel> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(SkillProfileCsvModel.class);
		
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		
		return defaultLineMapper;
	}
	
}
