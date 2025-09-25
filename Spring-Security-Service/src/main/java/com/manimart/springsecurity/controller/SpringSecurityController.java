package com.manimart.springsecurity.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringSecurityController {


    @GetMapping("/")
    public ResponseEntity<String> getHomePage(HttpServletRequest httpServletRequest) {
        //get the session id from Browser Cookies
        String sessionId = httpServletRequest.getSession().getId();
        return ResponseEntity.ok("Welcome to Mani Mart " + sessionId);
    }
}
