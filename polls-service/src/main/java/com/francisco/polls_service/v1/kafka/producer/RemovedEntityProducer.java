package com.francisco.polls_service.v1.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.francisco.polls_service.v1.event.RemovedEntityEvent;

@Component
public class RemovedEntityProducer {

	Logger logger = LoggerFactory.getLogger(RemovedEntityProducer.class);
	
	@Autowired
	KafkaTemplate<String, Object> kafkaTemplate;

	public void publishEvent(String entityName, Long entityId) {
		logger.info("Removed Entity event published for entity {} and id {}",entityName, entityId);
		kafkaTemplate.send("removedEntity",
				RemovedEntityEvent.builder().entityName(entityName).entityId(entityId).build());
	}

}
