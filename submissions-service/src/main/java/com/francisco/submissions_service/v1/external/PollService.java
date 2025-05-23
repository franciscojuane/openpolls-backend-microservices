package com.francisco.submissions_service.v1.external;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.francisco.submissions_service.v1.external.dto.PollResponse;
import com.francisco.submissions_service.v1.external.dto.QuestionResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


public class PollService {

	
	@Autowired
	PollServiceClient pollServiceClient;
	
	@CircuitBreaker(name="pollService")
	public PollResponse findByPollKey(String key) {
		return pollServiceClient.findByPollKey(key);
	};
	
	@CircuitBreaker(name="pollService")
	public PollResponse findByPollId(Long id) {
		return pollServiceClient.findByPollId(id);
	}
	
	@CircuitBreaker(name="pollService")
	public List<QuestionResponse> getQuestionsByPollId(Long pollId){
		return pollServiceClient.getQuestionsByPollId(pollId);
	}
	
}
