package com.example.SpringJWT.service;

import com.example.SpringJWT.domain.Role;
import com.example.SpringJWT.domain.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    Role saveROle(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getAllUser();
}
