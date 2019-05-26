package io.sc372.springbootjwtjava.service.impl;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import io.sc372.springbootjwtjava.domain.User;
import io.sc372.springbootjwtjava.dto.UserDto;
import io.sc372.springbootjwtjava.repository.RoleRepository;
import io.sc372.springbootjwtjava.repository.UserRepository;
import io.sc372.springbootjwtjava.service.UserService;
// import lombok.AllArgsConstructor;

@Service(value = "userService")
// @AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUser(UserDto userDto) {
        User newUser = new User();
        newUser.setUserEmail(userDto.getUserEmail());
        newUser.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        newUser.setRoles(new HashSet<>(roleRepository.findAll()));
        return userRepository.save(newUser);
    }

    @Override
    public User selectUserByEmail(String userEmail) {
        return userRepository.findByUserEmail(userEmail);
    }
}