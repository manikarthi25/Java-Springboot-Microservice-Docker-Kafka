package com.manimart.springsecurity.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {

    String generateJWTToken(String userName);

    String extractUserName(String token);

    boolean validateToken(String token, UserDetails userDetails);
}
