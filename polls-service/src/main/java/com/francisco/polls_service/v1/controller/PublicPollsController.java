package com.francisco.polls_service.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.francisco.polls_service.v1.dto.PollResponse;
import com.francisco.polls_service.v1.model.Poll;
import com.francisco.polls_service.v1.service.PollService;
import com.francisco.polls_service.v1.service.QuestionService;

import jakarta.validation.ValidationException;

@RestController
@RequestMapping("/public/polls")
public class PublicPollsController {
	
	@Autowired
	PollService pollService;
	
	@Autowired
	QuestionService questionService;
	
	
	@GetMapping("/{pollKey}")
	public ResponseEntity<?> getPublicPoll(@PathVariable String pollKey){
		Poll poll = pollService.findByPollKey(pollKey);
		if (poll==null ) {
			throw new ValidationException("Poll not found with provided key.");
		}
		PollResponse response = pollToPollResponse(poll);
		return ResponseEntity.ok(response);
	}

	private PollResponse pollToPollResponse(Poll poll) {
		PollResponse pollResponse = new PollResponse();
		pollResponse.setCreatedByUserId(poll.getCreatedByUserId());
		pollResponse.setName(poll.getName());
		pollResponse.setPollKey(poll.getPollKey());
		pollResponse.setDescription(poll.getDescription());
		pollResponse.setAmountOfQuestions(questionService.amountOfQuestionsForPoll(poll));
		pollResponse.setEffectiveDate(poll.getEffectiveDate());
		pollResponse.setExpirationDate(poll.getExpirationDate());
		pollResponse.setId(poll.getId());
		return pollResponse;
	}

}
