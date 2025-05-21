package com.francisco.polls_service.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.francisco.polls_service.v1.dto.QuestionResponse;
import com.francisco.polls_service.v1.dto.mappers.QuestionMapper;
import com.francisco.polls_service.v1.model.Question;
import com.francisco.polls_service.v1.service.QuestionService;

@RestController
@RequestMapping("/public/v1/polls/{pollKey}/questions")
public class PublicQuestionsController {
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	QuestionMapper questionMapper;
	
	@GetMapping("")
	public ResponseEntity<?> getQuestionsByPollKey(@PathVariable String pollKey) {
		List<Question> questions = questionService.findByPollKey(pollKey);
		List<QuestionResponse> questionResponse = questions.stream().map(elem -> questionMapper.questionToResponse(elem)).toList();
		return ResponseEntity.ok(questionResponse);
	}

	
	
}
