package com.francisco.polls_service.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfiguration {
	@Bean
	public NewTopic removedEntityTopic() {
	    return TopicBuilder.name("removedEntity")
	        .partitions(1)
	        .replicas(1)
	        .build();
	}
}
