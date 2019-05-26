package sc372.springbootjwtjava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sc372.springbootjwtjava.domain.ApiResponse;
import sc372.springbootjwtjava.domain.User;
import sc372.springbootjwtjava.service.UserService;
// import lombok.AllArgsConstructor;

@RestController
// @AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5394")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{userEmail}")
    public ApiResponse<List<User>> selectUserByEmail(@PathVariable String userEmail) {
        User user = userService.selectUserByEmail(userEmail);
        return new ApiResponse<>(HttpStatus.OK.value(), "요청이 성공하였습니다.", user);
    }
}