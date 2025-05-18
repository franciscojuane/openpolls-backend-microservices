package com.francisco.polls_service.v1.dto.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.francisco.polls_service.v1.dto.PollCreateRequest;
import com.francisco.polls_service.v1.dto.PollResponse;
import com.francisco.polls_service.v1.model.Poll;
import com.francisco.polls_service.v1.service.QuestionService;

@Component
public class PollMapper {

	@Autowired
	QuestionService questionService;
	
	public PollResponse pollToPollResponse(Poll poll) {
		PollResponse pollResponse = new PollResponse();
		pollResponse.setCreatedByUserId(poll.getCreatedByUserId());
		pollResponse.setId(poll.getId());
		pollResponse.setName(poll.getName());
		pollResponse.setPollKey(poll.getPollKey());
		pollResponse.setDescription(poll.getDescription());
		pollResponse.setAmountOfQuestions(questionService.amountOfQuestionsForPoll(poll));
		pollResponse.setEffectiveDate(poll.getEffectiveDate());
		pollResponse.setExpirationDate(poll.getExpirationDate());
		pollResponse.setId(poll.getId());
		pollResponse.setSubmissionLimitCriteria(poll.getSubmissionLimitCriteria());
		return pollResponse;
	}
	
	public Poll pollRequestToPoll(PollCreateRequest pollResponse) {
	    Poll poll = Poll.builder()
	        .name(pollResponse.getName())
	        .description(pollResponse.getDescription())
	        .build();
	    
	    poll.setId(pollResponse.getId());
	    poll.setEffectiveDate(pollResponse.getEffectiveDate());
        poll.setExpirationDate(pollResponse.getExpirationDate());
        poll.setSubmissionLimitCriteria(pollResponse.getSubmissionLimitCriteria());
        return poll;
	}
}
