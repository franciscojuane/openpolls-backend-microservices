package com.francisco.polls_service.v1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.francisco.polls_service.v1.model.Poll;
import com.francisco.polls_service.v1.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>{

	Long countByPoll(Poll poll);
	
	List<Poll> findByPoll(Poll poll);

	List<Question> findByPollId(Long pollId);
	
	void deleteByPollId(Long pollId);

	List<Question> findByPoll_PollKey(String pollKey);
	
}
