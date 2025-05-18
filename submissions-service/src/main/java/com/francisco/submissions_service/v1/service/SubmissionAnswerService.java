package com.francisco.submissions_service.v1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.francisco.submissions_service.v1.model.SubmissionAnswer;
import com.francisco.submissions_service.v1.repository.SubmissionAnswerRepository;

@Service
public class SubmissionAnswerService {

	@Autowired
	SubmissionAnswerRepository submissionAnswerRepository;
	
	public SubmissionAnswer save(SubmissionAnswer submissionAnswer) {
		return submissionAnswerRepository.save(submissionAnswer);
	}
	
	@PreAuthorize("hasAuthority('SUBMISSION_DELETE')")
	public void deleteByQuestionId(Long questionId) {
		submissionAnswerRepository.deleteByQuestionId(questionId);
	}
	
	@PreAuthorize("hasAuthority('SUBMISSION_DELETE')")
	public void deleteBySubmissionId(Long submissionId) {
		submissionAnswerRepository.deleteBySubmissionId(submissionId);
	}
	
	@PreAuthorize("hasAuthority('SUBMISSION_DELETE')")
	public void deleteByPollId(Long pollId) {
		submissionAnswerRepository.deleteBySubmission_PollId(pollId);
	}
	
	@PreAuthorize("hasAuthority('SUBMISSION_READ')")
	public List<SubmissionAnswer> findBySubmissionId(Long submissionId){
		return submissionAnswerRepository.findBySubmissionId(submissionId);
	}

	@PreAuthorize("hasAuthority('SUBMISSION_READ')")
	public List<SubmissionAnswer> findBySubmissionIdOrderById(Long submissionId) {
		return submissionAnswerRepository.findBySubmissionIdOrderById(submissionId);
	}
}
