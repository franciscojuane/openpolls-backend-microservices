package com.francisco.submissions_service.v1.model;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;

import com.francisco.submissions_service.v1.model.common.AbstractModel;
import com.francisco.submissions_service.v1.model.common.Constants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = Constants.TABLE_PREFIX + "SUBMISSION_ANSWER")
public class SubmissionAnswer extends AbstractModel{

	private Long questionId;
	
	@ManyToOne
	@JoinColumn(name="SUBMISSION_ID")
	private Submission submission;
	
	@Column(length=1000)
	private String answer;
	
}
