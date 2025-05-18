package com.francisco.submissions_service.v1.model;

import com.francisco.submissions_service.v1.model.common.AbstractModel;
import com.francisco.submissions_service.v1.model.common.Constants;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = Constants.TABLE_PREFIX + "SUBMISSION")
public class Submission extends AbstractModel{

	private String ipAddress;
	
	private String emailAddress;
	
	private Long pollId;
}
