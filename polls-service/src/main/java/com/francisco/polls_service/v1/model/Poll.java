package com.francisco.polls_service.v1.model;

import java.util.UUID;

import com.francisco.polls_service.v1.model.common.Constants;
import com.francisco.polls_service.v1.model.common.EffectiveModel;
import com.francisco.polls_service.v1.model.enums.SubmissionLimitCriteria;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = Constants.TABLE_PREFIX + "POLL")
@Entity
public class Poll extends EffectiveModel{

	@NotNull
	private String name;
	
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private Long createdByUserId;
	
	@Default
	private String pollKey = UUID.randomUUID().toString();
	
	private SubmissionLimitCriteria submissionLimitCriteria;
	
}
