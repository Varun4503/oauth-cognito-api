package com.demo.oauth_cognito_api.congif;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import com.demo.oauth_cognito_api.entities.AppUser;
import com.demo.oauth_cognito_api.repo.AppUserRepository;
import com.demo.oauth_cognito_api.services.AppUserService;

@Component
public class CustomJwtAuthConverter implements Converter<Jwt,AbstractAuthenticationToken> {

	//@Autowired
	//AppUserRepository repo;
	
	@Autowired
	AppUserService service;
	
	
	@Override
	public AbstractAuthenticationToken convert(Jwt source) {
		
		AppUser user=service.syncUser(source);
		
		List<GrantedAuthority> authorities= 
				List.of(new SimpleGrantedAuthority("ROLE_"+user.getRole().name()));
		
		return new JwtAuthenticationToken(source,authorities);
	}

}
