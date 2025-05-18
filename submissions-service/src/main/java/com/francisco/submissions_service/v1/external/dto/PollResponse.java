package com.francisco.submissions_service.v1.external.dto;

import com.francisco.submissions_service.v1.model.enums.SubmissionLimitCriteria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PollResponse {
	
	private Long id;

	private String name;
	
	private String description;
	
	private Long createdByUserId;
	
	private Long amountOfQuestions;
	
	private String pollKey;
	
	private SubmissionLimitCriteria submissionLimitCriteria;
}
