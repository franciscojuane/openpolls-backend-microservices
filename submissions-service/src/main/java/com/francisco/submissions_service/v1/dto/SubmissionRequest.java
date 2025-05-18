package com.francisco.submissions_service.v1.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubmissionRequest {

	private Long pollId;
	
	private List<SubmissionAnswerRequest> submissionAnswers;
	
	String ipAddress;
	
	String emailAddress;
}
