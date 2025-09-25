package com.manimart.springsecurity.service.impl;

import com.manimart.springsecurity.entity.UsersEntity;
import com.manimart.springsecurity.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepo usersRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UsersEntity usersEntity = usersRepo.findByUserName(userName);
        if (null == usersEntity) {
            System.out.println("This user is not found");
            throw new UsernameNotFoundException("This user is not found");
        }

        return new MyUserDetails(usersEntity);
    }
}
