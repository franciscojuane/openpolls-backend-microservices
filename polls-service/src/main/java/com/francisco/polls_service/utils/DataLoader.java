package com.francisco.polls_service.utils;

import java.time.LocalDateTime;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.francisco.polls_service.v1.model.Poll;
import com.francisco.polls_service.v1.model.Question;
import com.francisco.polls_service.v1.model.QuestionOption;
import com.francisco.polls_service.v1.model.enums.QuestionType;
import com.francisco.polls_service.v1.model.enums.SubmissionLimitCriteria;
import com.francisco.polls_service.v1.service.PollService;
import com.francisco.polls_service.v1.service.QuestionOptionService;
import com.francisco.polls_service.v1.service.QuestionService;

@Component
public class DataLoader implements InitializingBean {
	
	@Autowired
	PollService pollService;
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	QuestionOptionService questionOptionService;
	

	@Override
	public void afterPropertiesSet() throws Exception {
		
		Poll poll = Poll.builder().name("Poll 1").description("Poll 1 Description").submissionLimitCriteria(SubmissionLimitCriteria.NONE).
				createdByUserId(1L).build();

		poll.setEffectiveDate(LocalDateTime.now().minusDays(1));
		poll.setExpirationDate(LocalDateTime.now().plusDays(1));
		poll = pollService.save(poll);
		
		Question question1 = Question.builder()
				.questionType(QuestionType.MULTIPLE_CHOICE)
				.minAmountOfSelections(1)
				.maxAmountOfSelections(1)
				.text("Select your favorite animal")
				.subText("Only one option allowed")
				.poll(poll)
				.rank(1)
				.build();
		
		question1 = questionService.save(question1);
		
		QuestionOption questionOption11 = QuestionOption.builder().text("Cats").question(question1)
				.build();
		questionOption11 = questionOptionService.save(questionOption11);
		
		QuestionOption questionOption12 = QuestionOption.builder().text("Dogs").question(question1)
				.build();
		questionOption12 = questionOptionService.save(questionOption12);
		
		QuestionOption questionOption13 = QuestionOption.builder().text("Iguanas").question(question1)
				.build();
		questionOption13 = questionOptionService.save(questionOption13);
		
		QuestionOption questionOption14 = QuestionOption.builder().text("Parrots").question(question1)
				.build();
		questionOption14 = questionOptionService.save(questionOption14);
		
		Question question2 = Question.builder()
				.questionType(QuestionType.MULTIPLE_CHOICE)
				.minAmountOfSelections(1)
				.maxAmountOfSelections(3)
				.text("Select your favorites musical genres")
				.subText("Up to 3 selections")
				.poll(poll)
				.rank(2)
				.build();
		
		question2 = questionService.save(question2);
		
		QuestionOption questionOption21 = QuestionOption.builder().text("Pop").question(question2)
				.build();
		questionOption21 = questionOptionService.save(questionOption21);
		
		QuestionOption questionOption22 = QuestionOption.builder().text("Rock").question(question2)
				.build();
		questionOption22 = questionOptionService.save(questionOption22);
		
		QuestionOption questionOption23 = QuestionOption.builder().text("Jazz").question(question2)
				.build();
		questionOption23 = questionOptionService.save(questionOption23);
		
		QuestionOption questionOption24 = QuestionOption.builder().text("Classical").question(question2)
				.build();
		questionOption24 = questionOptionService.save(questionOption24);
		
		QuestionOption questionOption25 = QuestionOption.builder().text("Electro").question(question2)
				.build();
		questionOption25 = questionOptionService.save(questionOption25);
		
		QuestionOption questionOption26 = QuestionOption.builder().text("Blues").question(question2)
				.build();
		questionOption26 = questionOptionService.save(questionOption26);
		
		QuestionOption questionOption27 = QuestionOption.builder().text("Samba").question(question2)
				.build();
		questionOption27 = questionOptionService.save(questionOption27);
		
		
		Question question3 = Question.builder()
				.questionType(QuestionType.NUMERIC)
				.text("Enter your age:")
				.minValue(18)
				.maxValue(120)
				.scale(1)
				.poll(poll)
				.rank(3)
				.build();
		
		question3 = questionService.save(question3);
		
		
		Question question4 = Question.builder()
				.questionType(QuestionType.SCALE)
				.text("Enter the amount of people in your family:")
				.subText("Including yourself")
				.minValue(1)
				.maxValue(10)
				.scale(1)
				.poll(poll)
				.rank(4)
				.build();
		
		question4 = questionService.save(question4);

		
		Question question5 = Question.builder()
				.questionType(QuestionType.TEXT)
				.text("Enter your commments")
				.minLength(100)
				.maxLength(500)
				.poll(poll)
				.rank(5)
				.build();
		
		question5 = questionService.save(question5);
		
	}

}
