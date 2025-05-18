package com.francisco.submissions_service.v1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.francisco.submissions_service.v1.model.Submission;
import com.francisco.submissions_service.v1.repository.aggregated.AnswerCount;

public interface SubmissionRepository extends JpaRepository<Submission, Long>{
	
	void deleteByPollId(Long id);
	
	Page<Submission> findByPollId(Long pollId, Pageable pageable);

	Page<Submission> findByPollIdOrderById(Long pollId, Pageable pageable);
	
	@Query("SELECT sa.answer AS answer, COUNT(sa.id) AS count FROM SubmissionAnswer sa where sa.submission.pollId = :pollId and sa.questionId = :questionId GROUP BY sa.answer")
	Page<AnswerCount> getAnswerCountByPollIdAndQuestionId(@Param("pollId") Long pollId, @Param("questionId") Long questionId, Pageable pageable);

	@Query("SELECT sa.answer AS answer FROM SubmissionAnswer sa where sa.submission.pollId = :pollId and sa.questionId = :questionId")
	Page<String> getAnswersByPollIdAndQuestionId(@Param("pollId") Long pollId, @Param("questionId") Long questionId, Pageable pageable);
	
	@Query("SELECT count(s.id) FROM Submission s WHERE s.pollId = :pollId AND s.emailAddress = :emailAddress")
	long getAmountOfSubmissionsByPollIdAndEmailAddress(@Param("pollId") Long pollId, @Param("emailAddress") String emailAddress);
	
	@Query("SELECT count(s.id) FROM Submission s WHERE s.pollId = :pollId AND s.ipAddress = :ipAddress")
	long getAmountOfSubmissionsByPollIdAndIpAddress(@Param("pollId") Long pollId, @Param("ipAddress") String ipAddress);
}
