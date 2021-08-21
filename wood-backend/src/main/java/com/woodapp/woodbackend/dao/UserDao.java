package com.woodapp.woodbackend.dao;

import com.woodapp.woodbackend.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {
    
    int insertUser(UUID id, User user);

    default int insertUser(User user){
        UUID id = UUID.randomUUID();
        return insertUser(id, user);
    }

    List<User> selectAllUsers();

    Optional<User> selectUserById(UUID id);

    int deleteUserById(UUID id);

    int updateUserById(UUID id, User user);
}
