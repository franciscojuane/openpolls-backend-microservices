package com.francisco.polls_service.v1.dto;

import com.francisco.polls_service.v1.model.common.EffectiveModel;
import com.francisco.polls_service.v1.model.enums.SubmissionLimitCriteria;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PollCreateRequest extends EffectiveModel{

	@NotNull
	private String name;
	
	private String description;
	
	private SubmissionLimitCriteria submissionLimitCriteria;

}
