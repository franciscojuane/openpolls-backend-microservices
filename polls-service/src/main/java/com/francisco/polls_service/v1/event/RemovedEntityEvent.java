package com.francisco.polls_service.v1.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RemovedEntityEvent {

	private String entityName;
	
	private Long entityId;
}
