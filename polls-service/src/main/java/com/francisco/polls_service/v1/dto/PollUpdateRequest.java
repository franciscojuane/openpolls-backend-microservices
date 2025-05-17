package com.francisco.polls_service.v1.dto;

import com.francisco.polls_service.v1.model.common.EffectiveModel;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PollUpdateRequest extends EffectiveModel{

	@NotNull
	private String name;
	
	private boolean updateName;
	
	private String description;
	
	private boolean updateDescription;

}