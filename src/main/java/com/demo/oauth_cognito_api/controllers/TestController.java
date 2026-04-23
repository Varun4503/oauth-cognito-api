package com.demo.oauth_cognito_api.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {
	
	@GetMapping("/test")
    public String test(@AuthenticationPrincipal Jwt jwt) {
		System.out.println(jwt.getClaims());
        return "Hello " + jwt.getSubject() + jwt.getClaim("email");
    }
}
