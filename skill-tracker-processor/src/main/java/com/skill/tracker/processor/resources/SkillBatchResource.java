package com.skill.tracker.processor.resources;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skill.tracker.api.jaxb.types.SkillTrackerResponse;
import com.skill.tracker.processor.service.SkillAdminService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("skill/")
@Slf4j
public class SkillBatchResource {
	
	@Autowired
	JobLauncher jobLauncher;
	
	@Autowired
	Job job;
	
	@Autowired
	SkillAdminService skillAdminService;
	
	@GetMapping("load/csv")
	public BatchStatus loadCsvData() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		
		Map<String, JobParameter> parameters = new HashMap<>();
		parameters.put("time", new JobParameter(System.currentTimeMillis()));
		JobParameters jobParameters = new JobParameters(parameters);
		JobExecution jobExecution = jobLauncher.run(job, jobParameters);
		
		while(jobExecution.isRunning()) {
			log.info("...");
		}
		
		return jobExecution.getStatus();
	}
	
	@GetMapping("{criteria}/{criteriaValue}/{pageStart}")
	public SkillTrackerResponse retrieveDetails(@PathVariable("criteria") String criteria, @PathVariable("criteriaValue") String criteriaValue, @PathVariable("pageStart") int pageStart) {
		log.info("retrieveDetails , {} , {} ",criteria, criteriaValue);
		return skillAdminService.retrieveDocuments(criteria, criteriaValue, pageStart);
	}

}
