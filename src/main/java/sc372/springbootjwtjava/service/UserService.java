package sc372.springbootjwtjava.service;

import sc372.springbootjwtjava.domain.User;
import sc372.springbootjwtjava.dto.UserDto;

public interface UserService {
    User selectUserByEmail(String userEmail);
    User createUser(UserDto userDto);
}