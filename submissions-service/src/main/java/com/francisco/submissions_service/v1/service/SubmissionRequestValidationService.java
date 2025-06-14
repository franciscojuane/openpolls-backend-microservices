package com.francisco.submissions_service.v1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.francisco.submissions_service.v1.dto.SubmissionRequest;
import com.francisco.submissions_service.v1.external.PollServiceClient;
import com.francisco.submissions_service.v1.external.PollService;
import com.francisco.submissions_service.v1.external.dto.PollResponse;
import com.francisco.submissions_service.v1.external.dto.QuestionResponse;
import com.francisco.submissions_service.v1.model.enums.QuestionType;

@Service
public class SubmissionRequestValidationService {

	@Autowired
	PollService pollService;
	
	@Autowired
	@Lazy
	SubmissionService submissionService;
	
	
	public void validate(SubmissionRequest submissionRequest) {
		checkQuestionsAmountOfAnswers(submissionRequest);
		checkSubmissionLimits(submissionRequest);
	}

	private void checkSubmissionLimits(SubmissionRequest submissionRequest) {
		PollResponse pollResponse = pollService.findByPollIdWithInternalSecret(submissionRequest.getPollId());
		switch (pollResponse.getSubmissionLimitCriteria()) {
		case EMAIL:
			if (submissionService.getAmountOfSubmissionsByPollIdAndEmailAddress(pollResponse.getId(), submissionRequest.getEmailAddress()) > 0) {
				throw new RuntimeException("Email aldready answered this poll.");
			}
			break;
		case IP:
			if (submissionService.getAmountOfSubmissionsByPollIdAndIpAddress(pollResponse.getId(), submissionRequest.getIpAddress()) > 0) {
				throw new RuntimeException("IP aldready answered this poll.");
			}
			break;
		default:
			break;
		}
	}

	private void checkQuestionsAmountOfAnswers(SubmissionRequest submissionRequest) {
		List<QuestionResponse> questions = pollService.getQuestionsByPollIdWithInternalSecret(submissionRequest.getPollId());
		for(QuestionResponse question: questions) {
			int amountOfAnswersForQuestion = submissionRequest.getSubmissionAnswers().stream().filter(elem -> elem.getQuestionId() == question.getId()).toList().size();
			if (canHaveMoreThanOneAnswer(question)) {
				if (amountOfAnswersForQuestion < question.getMinAmountOfSelections() || amountOfAnswersForQuestion>question.getMaxAmountOfSelections()) {
					throw new RuntimeException("Illegal amount of answers for multiple choice question with id: " + question.getId());
				}
			} else {
			if (amountOfAnswersForQuestion == 0) {
				throw new RuntimeException("No answer found for question with id: " + question.getId());
			}
			if (amountOfAnswersForQuestion > 1) {
				throw new RuntimeException("More than one answer found for question with id: " + question.getId());
			}
			}
		}
	}
	
	private boolean canHaveMoreThanOneAnswer(QuestionResponse questionResponse) {
		return QuestionType.MULTIPLE_CHOICE.equals(questionResponse.getQuestionType()) && questionResponse.getMaxAmountOfSelections() > 1;
	}
	
	
}
