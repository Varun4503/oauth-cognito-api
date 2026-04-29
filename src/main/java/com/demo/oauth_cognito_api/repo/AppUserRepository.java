package com.demo.oauth_cognito_api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.oauth_cognito_api.entities.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,String>{

}
