package com.francisco.polls_service.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.francisco.polls_service.v1.dto.QuestionRequest;
import com.francisco.polls_service.v1.dto.QuestionResponse;
import com.francisco.polls_service.v1.dto.mappers.QuestionMapper;
import com.francisco.polls_service.v1.model.Question;
import com.francisco.polls_service.v1.service.PollService;
import com.francisco.polls_service.v1.service.QuestionService;

@RestController
@RequestMapping("/v1/polls/{pollId}/questions")
public class QuestionsController {

	@Autowired
    private QuestionService questionService;
	
	@Autowired
	private PollService pollService;
	
	@Autowired
	private QuestionMapper questionMapper;
	
	@GetMapping("")
	public ResponseEntity<?> getQuestionsByPollId(@PathVariable Long pollId) {
		List<Question> questions = questionService.findByPollId(pollId);
		List<QuestionResponse> questionResponse = questions.stream().map(elem -> questionMapper.questionToResponse(elem)).toList();
		return ResponseEntity.ok(questionResponse);
	}
	

	@PostMapping("")
	public ResponseEntity<?> createQuestionForPoll(@PathVariable Long pollId, @RequestBody QuestionRequest questionRequest) {
		Question question = questionMapper.requestToQuestion(questionRequest, pollId);
		question = questionService.save(question);
		return ResponseEntity.ok(questionMapper.questionToResponse(question));
	}
	
	@PatchMapping("/{questionId}")
	public ResponseEntity<?> updateQuestionForPoll(@RequestBody QuestionRequest questionRequest,@PathVariable Long pollId, @PathVariable Long questionId) {
		questionRequest.setId(questionId);
		Question question = questionService.update(questionMapper.requestToQuestion(questionRequest, pollId));
		System.out.println(question.getPoll());
		return ResponseEntity.ok(questionMapper.questionToResponse(question));
	}
	
	@DeleteMapping("/{questionId}")
	public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId){
		questionService.deleteById(questionId);
		return ResponseEntity.ok().build();
	}
	
	
	
	
}
