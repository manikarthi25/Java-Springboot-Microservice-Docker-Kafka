package com.manimart.springsecurity.controller;

import com.manimart.springsecurity.model.Users;
import com.manimart.springsecurity.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/add")
    public ResponseEntity<Users> addUsers(@RequestBody Users users) {
        Users usersResponse = usersService.addUsers(users);
        return new ResponseEntity<Users>(usersResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Users users) {
       String status = usersService.verify(users);
        return new ResponseEntity<String>(status, HttpStatus.OK);
    }

}
