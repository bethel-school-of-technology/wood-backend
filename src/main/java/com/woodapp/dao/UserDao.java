package com.woodapp.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.woodapp.models.User;

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
