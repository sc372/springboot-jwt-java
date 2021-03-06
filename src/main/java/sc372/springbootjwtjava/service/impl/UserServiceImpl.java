package sc372.springbootjwtjava.service.impl;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import sc372.springbootjwtjava.domain.User;
import sc372.springbootjwtjava.dto.UserDto;
import sc372.springbootjwtjava.repository.RoleRepository;
import sc372.springbootjwtjava.repository.UserRepository;
import sc372.springbootjwtjava.service.UserService;

@Service(value = "userService")
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
        newUser.setRoles(new HashSet<>(roleRepository.findByName(userDto.getRoleName())));

        return userRepository.save(newUser);
    }

    @Override
    public User selectUserByEmail(String userEmail) {
        return userRepository.findByUserEmail(userEmail);
    }
}