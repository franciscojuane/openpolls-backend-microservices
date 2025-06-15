package com.francisco.submissions_service.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.francisco.submissions_service.v1.dto.SubmissionTableResponse;
import com.francisco.submissions_service.v1.external.PollService;
import com.francisco.submissions_service.v1.external.dto.QuestionResponse;
import com.francisco.submissions_service.v1.model.Submission;
import com.francisco.submissions_service.v1.model.SubmissionAnswer;
import com.francisco.submissions_service.v1.repository.aggregated.AnswerCount;
import com.francisco.submissions_service.v1.service.SubmissionAnswerService;
import com.francisco.submissions_service.v1.service.SubmissionService;

@RestController
@RequestMapping("/v1/submissions")
public class SubmissionsController {

	@Autowired
	SubmissionService submissionService;
	
	@Autowired
	SubmissionAnswerService submissionAnswerService;
	
	@Autowired
	PollService pollService;
	
	
	@GetMapping("/byPoll/{pollId}/table")
	public ResponseEntity<?> generateTable(@PathVariable Long pollId, Pageable pageable){
		SubmissionTableResponse submissionsTable = generateSubmissionsMapForPollId(pollId, pageable);
		return ResponseEntity.ok(submissionsTable);
	}
	
	@PreAuthorize("hasAuthority('RESULTS_READ')")
	public SubmissionTableResponse generateSubmissionsMapForPollId(Long pollId, Pageable pageable) {
		SubmissionTableResponse submissionTableResponse = new SubmissionTableResponse();
		
		List<QuestionResponse> questions = pollService.getQuestionsByPollId(pollId);
		Page<Submission> submissions = submissionService.findByPollIdOrderById(pollId, pageable);
		for (Submission submission: submissions) {
			List<SubmissionAnswer> submissionAnswers = submissionAnswerService.findBySubmissionIdOrderById(submission.getId());
			submissionTableResponse.addRow(submission, submissionAnswers, questions);
		}
		return submissionTableResponse;
	}
	
	@GetMapping("/byPoll/{pollId}/answerCountByQuestion/{questionId}")
	public ResponseEntity<?> answerCountByQuestion(@PathVariable Long pollId, @PathVariable Long questionId, Pageable pageable){
		Page<AnswerCount> answerCount = submissionService.generateAnswerCountByPollIdAndQuestionId(pollId, questionId, pageable);
		return ResponseEntity.ok(answerCount);
	}
	
	@GetMapping("/byPoll/{pollId}/answersByQuestion/{questionId}")
	public ResponseEntity<?> answerByQuestion(@PathVariable Long questionId, @PathVariable Long pollId, Pageable pageable){
		Page<String> answers = submissionService.generateAnswersByPollIdAndQuestionId(pollId, questionId, pageable);
		return ResponseEntity.ok(answers);
	}
	
}
