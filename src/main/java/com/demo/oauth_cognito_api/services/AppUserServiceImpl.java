package com.demo.oauth_cognito_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.demo.oauth_cognito_api.entities.AppUser;
import com.demo.oauth_cognito_api.entities.Role;
import com.demo.oauth_cognito_api.repo.AppUserRepository;

@Service
public class AppUserServiceImpl implements AppUserService {

	@Autowired
	AppUserRepository repo;
	
	@Override
	public AppUser syncUser(Jwt jwt) {
		
		String sub=jwt.getSubject();
		
		return repo.findById(sub).orElseGet(()->{
			AppUser user=new AppUser();
			user.setId(sub);
			user.setEmail(jwt.getClaim("email"));
			
			user.setRole(Role.USER);
			
			return repo.save(user);
		});
	}

}
