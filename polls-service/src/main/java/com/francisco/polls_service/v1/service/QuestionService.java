package com.francisco.polls_service.v1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.francisco.polls_service.v1.model.Poll;
import com.francisco.polls_service.v1.model.Question;
import com.francisco.polls_service.v1.model.QuestionOption;
import com.francisco.polls_service.v1.repository.QuestionRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class QuestionService {

	@Autowired
    private QuestionRepository questionRepository;

	@PreAuthorize("hasAuthority('POLL_READ')")
    public Page<Question> findAll(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

	@PreAuthorize("hasAuthority('POLL_READ')")
    public Question findById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + id));
    }

    @Transactional
	@PreAuthorize("hasAuthority('POLL_CREATE')")
    public Question save(Question question) {
        return questionRepository.save(question);
    }

    @Transactional
	@PreAuthorize("hasAuthority('POLL_UPDATE')")
    public Question update(Question updatedQuestion) {
        Question existingQuestion = questionRepository.findById(updatedQuestion.getId())
                .orElseThrow(() -> new EntityNotFoundException("Question not found for update with id: " + updatedQuestion.getId()));

        existingQuestion.setRank(updatedQuestion.getRank());
        existingQuestion.setText(updatedQuestion.getText());
        existingQuestion.setSubText(updatedQuestion.getSubText());
        existingQuestion.setQuestionType(updatedQuestion.getQuestionType());
        existingQuestion.setMinAmountOfSelections(updatedQuestion.getMinAmountOfSelections());
        existingQuestion.setMaxAmountOfSelections(updatedQuestion.getMaxAmountOfSelections());
        List<QuestionOption> options = existingQuestion.getOptions();
        options.clear();
        for (QuestionOption option : updatedQuestion.getOptions()) {
        	options.add(option);
        }
        existingQuestion.setOptions(options);
        existingQuestion.setMinValue(updatedQuestion.getMinValue());
        existingQuestion.setMaxValue(updatedQuestion.getMaxValue());
        existingQuestion.setScale(updatedQuestion.getScale());
        existingQuestion.setMinLength(updatedQuestion.getMinLength());
        existingQuestion.setMaxLength(updatedQuestion.getMaxLength());

        return questionRepository.save(existingQuestion);
    }

    @Transactional
	@PreAuthorize("hasAuthority('POLL_UPDATE')")
    public void deleteById(Long questionId) {
        if (!questionRepository.existsById(questionId)) {
            throw new EntityNotFoundException("Question not found for id " + questionId);
        }
    	//submissionAnswerService.deleteByQuestionId(questionId);
        questionRepository.deleteById(questionId);
    }
    
    
    public Long amountOfQuestionsForPoll(Poll poll) {
    	return questionRepository.countByPoll(poll);
    }

	@PreAuthorize("hasAuthority('POLL_READ')")
	public List<Question> findByPollId(Long pollId) {
		return questionRepository.findByPollId(pollId);
	}


	public List<Question> findByPollKey(String pollKey) {
		return questionRepository.findByPoll_PollKey(pollKey);
	}
	
	@Transactional
	@PreAuthorize("hasAuthority('POLL_UPDATE')")
	public void deleteByPollId(Long pollId) {
		//submissionService.deleteSubmissionsByPollId(pollId);
		questionRepository.deleteByPollId(pollId);
		
	}
	
	public Question getReferenceById(Long questionId) {
		return questionRepository.getReferenceById(questionId);
	}
	
	
}
