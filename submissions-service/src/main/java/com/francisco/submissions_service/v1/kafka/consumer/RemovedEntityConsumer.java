package com.francisco.submissions_service.v1.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.francisco.submissions_service.v1.event.RemovedEntityEvent;
import com.francisco.submissions_service.v1.service.SubmissionAnswerService;
import com.francisco.submissions_service.v1.service.SubmissionService;

@Component
public class RemovedEntityConsumer {

	Logger logger = LoggerFactory.getLogger(RemovedEntityConsumer.class);

	@Autowired
	KafkaTemplate<String, Object> kafkaTemplate;

	@Autowired
	SubmissionService submissionService;

	@Autowired
	SubmissionAnswerService submissionAnswerService;

	@KafkaListener(topics = "removedEntity", groupId = "group-submission-service")
	public void processEvent(RemovedEntityEvent removedEntityEvent) {
		logger.info("Removed Entity event received for entity {} and id {}", removedEntityEvent.getEntityName(),
				removedEntityEvent.getEntityId());
		switch (removedEntityEvent.getEntityName()) {
		case "poll":
			submissionService.deleteSubmissionsByPollId(removedEntityEvent.getEntityId());
			break;
		case "question":
			submissionAnswerService.deleteByQuestionId(removedEntityEvent.getEntityId());
			break;
		}
	}

}
