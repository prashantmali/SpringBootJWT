package com.example.SpringJWT.controller;


import com.example.SpringJWT.domain.Role;
import com.example.SpringJWT.domain.User;
import com.example.SpringJWT.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("getAllUser")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getAllUser());
    }

    @PostMapping("user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        return ResponseEntity.ok().body(userService.saveUser(user));
    }

    @PostMapping("role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        return ResponseEntity.ok().body(userService.saveROle(role));
    }

    @PostMapping("role/addToUser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm roleUser){
        userService.addRoleToUser(roleUser.getUsername(),roleUser.getRoleName());
        return ResponseEntity.ok().build();
    }


}

@Data
class RoleToUserForm{
    private String username;
    private String roleName;

}