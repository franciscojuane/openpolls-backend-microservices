package com.francisco.submissions_service.v1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.francisco.submissions_service.v1.dto.SubmissionAnswerRequest;
import com.francisco.submissions_service.v1.dto.SubmissionRequest;
import com.francisco.submissions_service.v1.external.PollServiceClient;
import com.francisco.submissions_service.v1.model.Submission;
import com.francisco.submissions_service.v1.model.SubmissionAnswer;
import com.francisco.submissions_service.v1.repository.SubmissionRepository;
import com.francisco.submissions_service.v1.repository.aggregated.AnswerCount;

import jakarta.transaction.Transactional;

@Service
public class SubmissionService {

	@Autowired
	SubmissionRepository submissionRepository;

	@Autowired
	SubmissionAnswerService submissionAnswerService;

	@Autowired
	SubmissionRequestValidationService submissionRequestValidationService;
	
	@Autowired
	PollServiceClient pollServiceClient;

	@Transactional
	public void submit(SubmissionRequest submissionRequest) {
		submissionRequestValidationService.validate(submissionRequest);
		Submission submission = new Submission();
		submission.setIpAddress(submissionRequest.getIpAddress());
		submission.setEmailAddress(submissionRequest.getEmailAddress());
		submission.setPollId(submissionRequest.getPollId());
		submission = submissionRepository.save(submission);

		for (SubmissionAnswerRequest submissionAnswerRequest : submissionRequest.getSubmissionAnswers()) {
			String answer = submissionAnswerRequest.getAnswer();
			SubmissionAnswer submissionAnswer = SubmissionAnswer.builder().questionId(submissionAnswerRequest.getQuestionId()).answer(answer)
					.submission(submission).build();
			submissionAnswer = submissionAnswerService.save(submissionAnswer);
		}
	}

	@PreAuthorize("hasAuthority('POLL_DELETE')")
	public void deleteSubmissionsByPollId(Long pollId) {
		submissionAnswerService.deleteByPollId(pollId);
		submissionRepository.deleteByPollId(pollId);
	}

	@PreAuthorize("hasAuthority('SUBMISSION_DELETE')")
	public void deleteSubmissionById(Long id) {
		submissionRepository.deleteById(id);
	}

	@PreAuthorize("hasAuthority('SUBMISSION_READ')")
	public Page<Submission> findSubmissionsByPollId(Long pollId, Pageable pageable) {
		return submissionRepository.findByPollId(pollId, pageable);
	}

	@PreAuthorize("hasAuthority('SUBMISSION_READ')")
	public Page<Submission> findByPollIdOrderById(Long pollId, Pageable pageable) {
		// TODO Auto-generated method stub
		return submissionRepository.findByPollIdOrderById(pollId, pageable);
	}
	
	public Page<AnswerCount> generateAnswerCountByPollIdAndQuestionId(Long pollId, Long questionId, Pageable pageable) {
		return submissionRepository.getAnswerCountByPollIdAndQuestionId(pollId, questionId, pageable);
	}

	public Page<String> generateAnswersByPollIdAndQuestionId(Long pollId, Long questionId, Pageable pageable) {
		return submissionRepository.getAnswersByPollIdAndQuestionId(pollId, questionId, pageable);
	}
	
	public Long getAmountOfSubmissionsByPollIdAndEmailAddress(Long pollId, String emailAddress) {
		return submissionRepository.getAmountOfSubmissionsByPollIdAndEmailAddress(pollId, emailAddress);
	}
	
	public Long getAmountOfSubmissionsByPollIdAndIpAddress(Long pollId, String emailAddress) {
		return submissionRepository.getAmountOfSubmissionsByPollIdAndIpAddress(pollId, emailAddress);
	}

}
