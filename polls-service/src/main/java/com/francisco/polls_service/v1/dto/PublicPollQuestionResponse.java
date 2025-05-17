package com.francisco.polls_service.v1.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicPollQuestionResponse {

	public PollResponse poll;
	
	public List<QuestionResponse> questions; 
}
