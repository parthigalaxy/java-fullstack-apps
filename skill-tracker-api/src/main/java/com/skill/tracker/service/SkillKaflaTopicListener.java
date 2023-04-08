package com.skill.tracker.service;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.skill.tracker.api.jaxb.types.SkillTrackerRequest;
import com.skill.tracker.api.jaxb.types.SkillTrackerType;
import com.skill.tracker.util.JsonUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@KafkaListener(id = "${kafka.group.id}", topics = "${kafka.topic.admin}",containerFactory = "kafkaListenerContainerFactory")
public class SkillKaflaTopicListener {

	@KafkaHandler
	public void listenSkillAdminTopicGroup(SkillTrackerType skillTrackerType,
			@Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
		log.info("listenSillAdminTopicGroup >> partition >> {} >> {} ", partition, JsonUtil.convertToString(skillTrackerType));
	}
	
	@KafkaHandler
	public void listenSkillAdminTopicGroupSkillTrackerRequest(SkillTrackerRequest skillTrackerRequest,
			@Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
		log.info("listenSkillAdminTopicGroupSkillTrackerRequest >> partition >> {} >> {} ", partition, skillTrackerRequest);
	}

	@KafkaHandler(isDefault = true)
    public void unknown(Object object) {
		log.info("Unkown type received: {} ", JsonUtil.convertToString(object));
    }
}
