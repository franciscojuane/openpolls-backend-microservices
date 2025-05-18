package com.francisco.submissions_service.v1.dto;

import com.francisco.submissions_service.v1.model.common.AbstractModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionResponse extends AbstractModel {
	
	private String ipAddress;

	private String emailAddress;

	private Long pollId;
}
