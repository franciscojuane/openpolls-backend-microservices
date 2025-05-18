package com.francisco.submissions_service.v1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.francisco.submissions_service.v1.model.SubmissionAnswer;

public interface SubmissionAnswerRepository extends JpaRepository<SubmissionAnswer, Long>{

	void deleteBySubmissionId(Long id);
	
	void deleteByQuestionId(Long questionId);
	
	void deleteBySubmission_PollId(Long pollId);

	List<SubmissionAnswer> findBySubmissionId(Long submissionId);

	List<SubmissionAnswer> findBySubmissionIdOrderById(Long submissionId);
}
