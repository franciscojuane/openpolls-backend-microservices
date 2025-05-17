package com.francisco.users_service.v1.model.common;

import java.time.LocalDateTime;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class EffectiveModel extends AbstractModel {

	private static final long serialVersionUID = 1L;

	public LocalDateTime effectiveDate;
	
	public LocalDateTime expirationDate;
	
	public boolean isEffective() {
		return this.isEffective(LocalDateTime.now());
	}
	
	 public boolean isEffective(LocalDateTime date) {
	        if (date == null) {
	            throw new RuntimeException("Effectiveness can't be calculated for null date"); 
	        }
	        
	        if (effectiveDate == null && expirationDate == null) {
	            return true;
	        }
	        
	        if (effectiveDate == null && expirationDate != null) {
	            return !(date.isEqual(expirationDate) || date.isAfter(expirationDate)); 
	        }
	        
	        if (effectiveDate != null && expirationDate == null) {
	            return date.isEqual(effectiveDate) || date.isAfter(effectiveDate);
	        }
	        
	        return (date.isAfter(effectiveDate) || date.isEqual(effectiveDate)) && date.isBefore(expirationDate) ; 
	    }
}
