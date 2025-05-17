package com.francisco.polls_service.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.francisco.polls_service.v1.model.QuestionOption;

public interface QuestionOptionRepository extends JpaRepository<QuestionOption, Long> {

}
