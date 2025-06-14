package com.francisco.submissions_service.v1.external;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.francisco.submissions_service.v1.external.dto.PollResponse;
import com.francisco.submissions_service.v1.external.dto.QuestionResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Component
public class PollService {

	
	@Value("${security.internal-secret}")
	String internalSecret;
	
	@Autowired
	PollServiceClient pollServiceClient;
	
	@CircuitBreaker(name="pollService")
	public PollResponse findByPollKey(String key) {
		return pollServiceClient.findByPollKey(key);
	};
	
	@CircuitBreaker(name="pollService")
	public PollResponse findByPollKeyWithInternalSecret(String key) {
		return pollServiceClient.findByPollKeyWithInternalSecret(key, internalSecret);
	};
	
	@CircuitBreaker(name="pollService")
	public PollResponse findByPollId(Long id) {
		return pollServiceClient.findByPollId(id);
	}
	
	@CircuitBreaker(name="pollService")
	public PollResponse findByPollIdWithInternalSecret(Long id) {
		return pollServiceClient.findByPollIdWithInternalSecret(id, internalSecret);
	}
	
	
	@CircuitBreaker(name="pollService")
	public List<QuestionResponse> getQuestionsByPollId(Long pollId){
		return pollServiceClient.getQuestionsByPollId(pollId);
	}
	
	@CircuitBreaker(name="pollService")
	public List<QuestionResponse> getQuestionsByPollIdWithInternalSecret(Long pollId){
		return pollServiceClient.getQuestionsByPollIdWithInternalSecret(pollId, internalSecret);
	}
	
}
