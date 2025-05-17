package com.francisco.polls_service.v1.dto.mappers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.francisco.polls_service.v1.dto.QuestionRequest;
import com.francisco.polls_service.v1.dto.QuestionResponse;
import com.francisco.polls_service.v1.model.Question;
import com.francisco.polls_service.v1.model.QuestionOption;
import com.francisco.polls_service.v1.service.PollService;

@Component
public class QuestionMapper {

		@Autowired
		PollService pollService;

	   public QuestionResponse questionToResponse(Question question) {
	        QuestionResponse response = QuestionResponse.builder()
	            .text(question.getText())
	            .subText(question.getSubText())
	            .questionType(question.getQuestionType())
	            .minAmountOfSelections(question.getMinAmountOfSelections())
	            .maxAmountOfSelections(question.getMaxAmountOfSelections())
	            .pollId(question.getPoll().getId())
	            .minValue(question.getMinValue())
	            .maxValue(question.getMaxValue())
	            .scale(question.getScale())
	            .rank(question.getRank())
	            .minLength(question.getMinLength())
	            .maxLength(question.getMaxLength())
	            .build();
	        
	        if (question.getOptions()!=null) {
	            response.setOptions(question.getOptions().stream().map(elem -> elem.getText()).toList());
	        }
	        
	        response.setId(question.getId());
	        return response;
	    }
	   
	   public Question requestToQuestion(QuestionRequest request, Long pollId) {
		    Question question = Question.builder()
		        .text(request.getText())
		        .subText(request.getSubText())
		        .questionType(request.getQuestionType())
		        .minAmountOfSelections(request.getMinAmountOfSelections())
		        .maxAmountOfSelections(request.getMaxAmountOfSelections())
		        .minValue(request.getMinValue())
		        .maxValue(request.getMaxValue())
		        .scale(request.getScale())
		        .rank(request.getRank())
		        .minLength(request.getMinLength())
		        .maxLength(request.getMaxLength())
		        .build();

		        question.setId(request.getId());
		        
		        question.setPoll(pollService.findById(pollId));
		        
		    if (request.getOptions() != null) {
		        List<QuestionOption> options = request.getOptions().stream()
		            .map(optionText -> QuestionOption.builder()
		                .text(optionText)
		                .question(question) 
		                .build())
		            .toList();
		        question.setOptions(options);
		    }

		    return question;
		}
	
}
