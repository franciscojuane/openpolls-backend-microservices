package com.francisco.polls_service.v1.controller;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.francisco.polls_service.v1.dto.PollCreateRequest;
import com.francisco.polls_service.v1.dto.PollResponse;
import com.francisco.polls_service.v1.dto.mappers.PollMapper;
import com.francisco.polls_service.v1.model.Poll;
import com.francisco.polls_service.v1.service.PollService;
import com.francisco.polls_service.v1.service.QuestionService;

import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;

@RestController
@RequestMapping("/v1/polls")
public class PollsController {

	@Autowired
	PollService pollService;
	
	@Autowired 
	QuestionService questionService;
	
	@Autowired
	PollMapper pollMapper;

	@GetMapping("")
	public ResponseEntity<?> getPolls(Pageable pageable) {
		Page<Poll> pollPage = pollService.findAll(pageable);
		Page<PollResponse> pollResponsePage = pollPage.map(new Function<Poll, PollResponse>() {
			@Override
			public PollResponse apply(Poll poll) {
				return pollMapper.pollToPollResponse(poll);
			}
		});
		return ResponseEntity.ok(pollResponsePage);
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<?> getPollById(@PathVariable Long id) {
		Poll poll = pollService.findById(id);
		PollResponse pollResponse = pollMapper.pollToPollResponse(poll);
		return ResponseEntity.ok(pollResponse);
	}
	
	@GetMapping("/byPollKey/{key}")
	public ResponseEntity<?> getPollByKey(@PathVariable String key) {
		Poll poll = pollService.findByPollKey(key);
		if (poll == null)
			throw new ValidationException("Poll not found");
		PollResponse pollResponse = pollMapper.pollToPollResponse(poll);
		return ResponseEntity.ok(pollResponse);
	}

	@PostMapping("")
	@Transactional
	public ResponseEntity<?> createPoll(Long userId, @RequestBody PollCreateRequest pollCreateRequest) {
		Poll poll = pollMapper.pollRequestToPoll(pollCreateRequest);
		poll.setCreatedByUserId(userId);
		poll = pollService.save(poll);
		return ResponseEntity.ok(pollMapper.pollToPollResponse(poll));
	}
	
	@PatchMapping("/{pollId}")
	public ResponseEntity<?> patchPoll(@RequestBody PollCreateRequest pollCreateRequest, @PathVariable Long pollId) {
		Poll updatedPoll = pollService.update(pollMapper.pollRequestToPoll(pollCreateRequest));
		return ResponseEntity.ok(pollMapper.pollToPollResponse(updatedPoll));
	}
	
	@DeleteMapping("/{pollId}")
	public ResponseEntity<?> deletePoll(@PathVariable Long pollId){
		pollService.deleteById(pollId);
		return ResponseEntity.ok().build();
	}
	


}
