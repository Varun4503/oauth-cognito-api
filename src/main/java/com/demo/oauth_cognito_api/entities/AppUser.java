package com.demo.oauth_cognito_api.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class AppUser {

	@Id
	private String id;
	
	private String email;
	
	@Enumerated(EnumType.STRING)
	private Role role;
}
