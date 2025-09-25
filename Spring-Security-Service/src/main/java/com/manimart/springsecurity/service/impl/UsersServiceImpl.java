package com.manimart.springsecurity.service.impl;

import com.manimart.springsecurity.entity.UsersEntity;
import com.manimart.springsecurity.model.Users;
import com.manimart.springsecurity.repo.UsersRepo;
import com.manimart.springsecurity.service.JWTService;
import com.manimart.springsecurity.service.UsersService;
import com.manimart.springsecurity.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;
    private ModelMapperUtil mapperUtil = new ModelMapperUtil();

    //12 arg - number of rounds plain-test - hashing-1 round -> hashing-2 round up to 12 round hashing
    private BCryptPasswordEncoder encoder =new BCryptPasswordEncoder(12);

    @Override
    public Users addUsers(Users users) {
        users.setPassword(encoder.encode(users.getPassword())); //encode the password
        UsersEntity usersEntity = usersRepo.save(mapperUtil.convertToUsersEntity(users));
        return mapperUtil.convertToUsersModel(usersEntity);
    }

    @Override
    public String verify(Users users) {
        UsersEntity usersEntity = mapperUtil.convertToUsersEntity(users);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(users.getUserName(), users.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateJWTToken(users.getUserName());
        }
        return "failed";
    }

}
