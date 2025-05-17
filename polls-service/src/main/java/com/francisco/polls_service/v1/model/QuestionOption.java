package com.francisco.polls_service.v1.model;

import com.francisco.polls_service.v1.model.common.Constants;
import com.francisco.polls_service.v1.model.common.EffectiveModel;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = Constants.TABLE_PREFIX + "MULTIPLE_CHOICE_QUESTION_OPTION")
public class QuestionOption extends EffectiveModel{
	
	private String text;
	
	@ManyToOne
	@JoinColumn(name = "MULTIPLE_CHOICE_QUESTION_ID")
	private Question question;

}
