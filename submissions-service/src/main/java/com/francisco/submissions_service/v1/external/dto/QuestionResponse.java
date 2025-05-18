package com.francisco.submissions_service.v1.external.dto;

import java.util.List;

import com.francisco.submissions_service.v1.model.common.AbstractModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionResponse extends AbstractModel {

	private int rank;

	private String text;

	private String subText;

	private String questionType;

	private Long pollId;

	/*
	 * For Multiple Choice Questions
	 */
	private Integer minAmountOfSelections;

	/*
	 * For Multiple Choice Questions
	 */
	private Integer maxAmountOfSelections;

	/*
	 * For Multiple Choice Questions
	 */
	private List<String> options;

	/*
	 * For Numeric and Scale Questions
	 */
	private Integer minValue;

	/*
	 * For Numeric and Scale Questions
	 */
	private Integer maxValue;

	/*
	 * For Scale Questions
	 */
	private Integer scale;

	/*
	 * For Text Questions
	 */
	private Integer minLength;

	/*
	 * For Text Questions
	 */
	private Integer maxLength;

}
