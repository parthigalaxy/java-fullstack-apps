package com.skill.tracker.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper;
import org.springframework.kafka.support.mapping.Jackson2JavaTypeMapper.TypePrecedence;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skill.tracker.api.jaxb.types.SkillTrackerRequest;
import com.skill.tracker.api.jaxb.types.SkillTrackerType;

import lombok.extern.slf4j.Slf4j;

@EnableKafka
@Configuration
@Slf4j
public class SkillKafkaConfig {

	@Value("${kafka.bootstrap.server}")
	private String kafkaBootstrapServer;

	@Value("${kafka.group.id}")
	private String kafkaGroupId;

	@Value("${kafka.topic.admin}")
	private String kafkaTopicAdmin;
	
	@Value("${kafka.topic.update}")
	private String kafkaTopicUpdate;

	@Bean
	public KafkaAdmin kafkaAdmin() {
		log.debug("kafkaAdmin >> {}", kafkaBootstrapServer);
		Map<String, Object> configs = new HashMap<>();
		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServer);
		return new KafkaAdmin(configs);
	}

	@Bean
	public NewTopic topicSkillAdmin() {
		return new NewTopic(kafkaTopicAdmin, 1, (short) 1);
	}
	
	@Bean
	public NewTopic topicSkillUpdate() {
		return new NewTopic(kafkaTopicUpdate, 1, (short) 1);
	}

	@Bean
	public ProducerFactory<String, Object> producerFactory() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServer);
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		configProps.put(JsonSerializer.TYPE_MAPPINGS, "SkillTrackerType:com.skill.tracker.api.jaxb.types.SkillTrackerType,"
				+ "SkillTrackerRequest:com.skill.tracker.api.jaxb.types.SkillTrackerRequest");
		return new DefaultKafkaProducerFactory<>(configProps);
	}

	@Bean
	public KafkaTemplate<String, Object> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}
	
	

	@Bean
	public ConsumerFactory<String, Object> multiTypeConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServer);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaGroupId);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		return new DefaultKafkaConsumerFactory<String, Object>(props);
	}
	
	@Bean
	public RecordMessageConverter multiTypeConverter() {
		ObjectMapper objectMapper = new ObjectMapper();
	    StringJsonMessageConverter converter = new StringJsonMessageConverter(objectMapper);
	    DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
	    typeMapper.setTypePrecedence(TypePrecedence.TYPE_ID);
	    typeMapper.addTrustedPackages("com.skill.tracker.api.jaxb.types");
	    Map<String, Class<?>> mappings = new HashMap<>();
	    mappings.put("SkillTrackerType", SkillTrackerType.class);
	    mappings.put("SkillTrackerRequest", SkillTrackerRequest.class);
	    typeMapper.setIdClassMapping(mappings);
	    converter.setTypeMapper(typeMapper);
	    return converter;
	}

	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Object>> kafkaListenerContainerFactory() {

		ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(multiTypeConsumerFactory());
		factory.setMessageConverter(multiTypeConverter());
		factory.afterPropertiesSet();
		return factory;
	}

}
