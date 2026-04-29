package com.demo.oauth_cognito_api.services;

import org.springframework.security.oauth2.jwt.Jwt;

import com.demo.oauth_cognito_api.entities.AppUser;

public interface AppUserService {
	AppUser syncUser(Jwt jwt);
}
