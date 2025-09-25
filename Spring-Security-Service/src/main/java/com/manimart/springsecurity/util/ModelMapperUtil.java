package com.manimart.springsecurity.util;

import com.manimart.springsecurity.entity.UsersEntity;
import com.manimart.springsecurity.model.Users;
import org.modelmapper.ModelMapper;

public class ModelMapperUtil {

    ModelMapper modelMapper = new ModelMapper();
    public Users convertToUsersModel(UsersEntity usersEntity) {
        return modelMapper.map(usersEntity, Users.class);
    }

    public UsersEntity convertToUsersEntity(Users users) {
        return modelMapper.map(users, UsersEntity.class);
    }
}
