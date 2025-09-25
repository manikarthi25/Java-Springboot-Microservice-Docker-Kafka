package com.manimart.springsecurity.repo;

import com.manimart.springsecurity.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<UsersEntity, Integer> {
    UsersEntity findByUserName(String userName);
}
