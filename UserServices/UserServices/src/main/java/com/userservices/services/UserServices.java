package com.userservices.services;

import com.userservices.entity.UserEntity;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.User;

import java.util.List;

public interface UserServices {
    public UserEntity saveUser(UserEntity user);
    public List<UserEntity> getAllUser();
    public UserEntity getUser(String userid);
    public void deleteUser(String UserId);
    public UserEntity updateUser(UserEntity user, String userID);

}
