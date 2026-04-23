package com.demo.oauth_cognito_api.congif;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) {
		http
			.csrf(c->c.disable())
			.cors(Customizer.withDefaults())
			.authorizeHttpRequests(auth -> auth
					.requestMatchers("/api/public").permitAll()
					.anyRequest().authenticated())
			.oauth2ResourceServer(oauth->oauth.jwt(Customizer.withDefaults()));
		
		return http.build();
	}
}
