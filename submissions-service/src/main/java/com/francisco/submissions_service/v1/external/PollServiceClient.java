package com.francisco.submissions_service.v1.external;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.francisco.submissions_service.v1.external.dto.PollResponse;
import com.francisco.submissions_service.v1.external.dto.QuestionResponse;

@FeignClient("poll-service")
public interface PollServiceClient {

	@RequestMapping(value="/v1/polls/byPollKey/{key}", method = RequestMethod.GET)
	public PollResponse findByPollKey(@PathVariable String key);
	
	
	@RequestMapping(value="/v1/polls/{id}", method = RequestMethod.GET)
	public PollResponse findByPollId(@PathVariable Long id);
	
	@RequestMapping(value = "/v1/polls/{pollId}/questions", method = RequestMethod.GET)
	public List<QuestionResponse> getQuestionsByPollId(Long pollId);
	
}
