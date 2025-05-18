package com.francisco.submissions_service.v1.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.francisco.submissions_service.v1.external.dto.QuestionResponse;
import com.francisco.submissions_service.v1.model.Submission;
import com.francisco.submissions_service.v1.model.SubmissionAnswer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionTableResponse {
	
	@Default
	List<SubmissionTableResponseRow> rows = new ArrayList<>();

	public void addRow(Submission submission, List<SubmissionAnswer> submissionAnswers, List<QuestionResponse> questionsResponses) {
		SubmissionTableResponseRow submissionTableResponseRow = new SubmissionTableResponseRow();
		submissionTableResponseRow.setIpAddress(submission.getIpAddress());
		submissionTableResponseRow.setEmailAddress(submission.getEmailAddress());
		submissionTableResponseRow.setPollId(submission.getPollId());

		List<SubmissionAnswerResponse> data = new ArrayList<>();
		for(QuestionResponse questionResponse : questionsResponses) {
			 List<SubmissionAnswer> submissionAnswersInt = submissionAnswers.stream().filter(elem -> elem.getQuestionId() == questionResponse.getId()).toList();
			 String answer = submissionAnswersInt.stream().map(elem -> elem.getAnswer().toString()).collect(Collectors.joining(","));
			 data.add(SubmissionAnswerResponse.builder().answer(answer).questionId(questionResponse.getId()).build());
		}
		
		submissionTableResponseRow.setData(data);
		rows.add(submissionTableResponseRow);
	}

}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class SubmissionTableResponseRow {

	private String ipAddress;

	private String emailAddress;

	private Long pollId;

	public List<SubmissionAnswerResponse> data;

}