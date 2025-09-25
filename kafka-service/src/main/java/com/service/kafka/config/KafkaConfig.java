package com.service.kafka.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Configuration
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class KafkaConfig {

	@Value(value = "${kafka.bootstrap.servers}")
	private String bootstrapAddress;

	@Value(value = "${kafka.consumer.group.id}")
	private String groupId;

	@Value("${kafka.producer.topic.name}")
	private String poTopicName;

}
