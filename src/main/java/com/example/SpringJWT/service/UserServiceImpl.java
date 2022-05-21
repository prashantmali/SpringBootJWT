package com.example.SpringJWT.service;

import com.example.SpringJWT.domain.Role;
import com.example.SpringJWT.domain.User;
import com.example.SpringJWT.repo.RoleRepo;
import com.example.SpringJWT.repo.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            log.error("user not found in Database");
            throw  new UsernameNotFoundException("user not found");

        }else{
            log.info("user  found in Database" + username);
        }

        Collection<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new  org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),grantedAuthorities);
    }


    @Override
    public User saveUser(User user) {
        log.info("saving user"+user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Role saveROle(Role role) {
        log.info("saving role"+role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("addRoleToUser"+username," "+roleName);
       User user = userRepository.findByUsername(username);
       Role role = roleRepo.findByName(roleName);
       user.getRoles().add(role);

    }

    @Override
    public User getUser(String username) {
        log.info("get user"+username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAllUser() {
        log.info("getAllUser() ");
        return userRepository.findAll();
    }


}
