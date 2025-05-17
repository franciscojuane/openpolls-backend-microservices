package com.francisco.users_service.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.francisco.users_service.model.common.Constants;
import com.francisco.users_service.model.common.EffectiveModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Constants.TABLE_PREFIX + "USER")
@Entity
@Builder
@EqualsAndHashCode(callSuper = false)
public class User extends EffectiveModel {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false, length = 50)
	private String firstName;

	@Column(nullable = false, length = 50)
	private String lastName;

	@Column(nullable = false)
	@JsonIgnore
	private String password;

	@Column(nullable = false, unique = true)
	private String email;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = Constants.TABLE_PREFIX + "USER_ROLES", 
			   joinColumns = @JoinColumn(name = "user_id"), 
			   inverseJoinColumns = @JoinColumn(name = "role_id"))
	@Default
	private Set<Role> roles = new HashSet<>();


	public boolean isAccountNonExpired() {
		return this.isEffective();
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	public boolean isEnabled() {
		return true;
	}

}
