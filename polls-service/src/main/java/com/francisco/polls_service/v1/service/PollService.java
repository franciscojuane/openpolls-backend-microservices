package com.francisco.polls_service.v1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.francisco.polls_service.v1.model.Poll;
import com.francisco.polls_service.v1.repository.PollRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class PollService {

	@Autowired
	PollRepository pollRepository;
	
	@Autowired
	QuestionService questionService;
	
	@PreAuthorize("hasAuthority('POLL_READ')")
	public Page<Poll> findAll(Pageable pageable){
		return pollRepository.findAll(pageable);
	}
	
	public Poll findById(Long id) {
        return pollRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Poll not found with id: " + id));
    }
	
	@Transactional
	@PreAuthorize("hasAuthority('POLL_CREATE')")
	public Poll save(Poll poll) {
        return pollRepository.save(poll);
    }
	
    @Transactional
    @PreAuthorize("hasAuthority('POLL_UPDATE')")
    public Poll update(Poll updatedPoll) {
        Poll existingPoll = pollRepository.findById(updatedPoll.getId())
                .orElseThrow(() -> new EntityNotFoundException("Poll not found for updating with id: " + updatedPoll.getId()));

        existingPoll.setName(updatedPoll.getName());
        existingPoll.setDescription(updatedPoll.getDescription());
        existingPoll.setEffectiveDate(updatedPoll.getEffectiveDate());
        existingPoll.setExpirationDate(updatedPoll.getExpirationDate());
        existingPoll.setSubmissionLimitCriteria(updatedPoll.getSubmissionLimitCriteria());
        
        return pollRepository.save(existingPoll);
    }

    @Transactional
    @PreAuthorize("hasAuthority('POLL_DELETE')")
    public void deleteById(Long pollId) {
        if (!pollRepository.existsById(pollId)) {
            throw new EntityNotFoundException("Poll not found for id " + pollId);
        }
        questionService.deleteByPollId(pollId);
        pollRepository.deleteById(pollId);
    }

    public Poll getReferenceById(Long pollId) {
    	return pollRepository.getReferenceById(pollId);
    }
    
	public Poll findByPollKey(String pollKey) {
		// TODO Auto-generated method stub
		return pollRepository.findByPollKey(pollKey);
	}
	
}
