package com.francisco.polls_service.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.francisco.polls_service.v1.model.Poll;

public interface PollRepository extends JpaRepository<Poll, Long> {

	Poll findByPollKey(String pollKey);

}
