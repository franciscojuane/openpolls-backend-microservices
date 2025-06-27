package com.francisco.submissions_service.v1.kafka.consumer;

import org.apache.avro.generic.GenericRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

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
	public void processEvent(GenericRecord genericRecord) {
		String entityName = genericRecord.get("entityName").toString();
		Long entityId = Long.parseLong(genericRecord.get("entityId").toString());
		logger.info("Removed Entity event received for entity {} and id {}", genericRecord.get("entityName"),
				genericRecord.get("entityId") );
		switch (entityName) {
		case "poll":
			submissionService.deleteSubmissionsByPollId(entityId);
			break;
		case "question":
			submissionAnswerService.deleteByQuestionId(entityId);
			break;
		}
	}
}
