package com.manimart.springsecurity.service;

import com.manimart.springsecurity.model.Users;
import org.springframework.web.bind.annotation.RequestBody;

public interface UsersService {

    Users addUsers(Users users);

    String verify(Users users);
}
