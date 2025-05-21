package com.francisco.submissions_service.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.francisco.submissions_service.v1.dto.SubmissionRequest;
import com.francisco.submissions_service.v1.external.PollServiceClient;
import com.francisco.submissions_service.v1.external.dto.PollResponse;
import com.francisco.submissions_service.v1.service.SubmissionAnswerService;
import com.francisco.submissions_service.v1.service.SubmissionService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/public/v1/submissions")
public class PublicSubmissionsController {

	@Autowired
	SubmissionService submissionService;
	
	@Autowired
	SubmissionAnswerService submissionAnswerService;
	
	@Autowired
	PollServiceClient pollServiceClient;
	
	@PostMapping("/byPoll/{pollKey}")
	public ResponseEntity<?> submit(@RequestBody SubmissionRequest submissionRequest, @PathVariable String pollKey, HttpServletRequest httpServletRequest){
		PollResponse pollResponse = pollServiceClient.findByPollKey(pollKey);
		if (pollResponse == null) 
			throw new IllegalArgumentException("Poll not found for provided key.");
		submissionRequest.setPollId(pollResponse.getId());
		submissionRequest.setIpAddress(getClientIp(httpServletRequest));
		submissionService.submit(submissionRequest);
		return ResponseEntity.ok().build();
	}
	
	
	public String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
	
}
