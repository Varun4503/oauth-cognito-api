package com.demo.oauth_cognito_api.congif;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.demo.oauth_cognito_api.entities.AppUser;
import com.demo.oauth_cognito_api.entities.Role;
import com.demo.oauth_cognito_api.repo.AppUserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/*
 * DESIGN NOTE:
 *
 * Initially implemented a OncePerRequestFilter to sync users from JWT
 * into the database before role assignment.
 *
 * However, in Spring Security, JWT validation and conversion happens
 * inside BearerTokenAuthenticationFilter. The custom JwtAuthenticationConverter
 * is invoked during this phase, before filters added via addFilterAfter().
 *
 * To execute this filter earlier, it would have to be placed before the
 * BearerTokenAuthenticationFilter, which would require manual JWT parsing
 * from the request header (since SecurityContext is not yet populated).
 *
 * To avoid this complexity and maintain a clean design, user synchronization
 * logic was moved into the JwtAuthenticationConverter, where the decoded Jwt
 * is already available.
 *
 * This approach ensures:
 * - No manual JWT parsing
 * - Proper execution order
 * - Simpler and more maintainable authentication flow
 */

@Component
public class UserSyncFilter extends OncePerRequestFilter{
	
	@Autowired
	AppUserRepository repo;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		
		if(auth instanceof JwtAuthenticationToken jAuth) {
			
			Jwt jwt=jAuth.getToken();
			String sub=jwt.getSubject();
			
			repo.findById(sub).orElseGet(()->{
				AppUser user=new AppUser();
				user.setId(sub);
				user.setRole(Role.USER);
				user.setEmail(jwt.getClaim("email"));
				
				return repo.save(user);
			});
			
			filterChain.doFilter(request, response);
		}
		
	}

}