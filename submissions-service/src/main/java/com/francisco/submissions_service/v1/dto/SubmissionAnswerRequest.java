package com.francisco.submissions_service.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionAnswerRequest {

	private Long questionId;

	private String answer;
	
}
