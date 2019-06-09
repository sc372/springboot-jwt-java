package sc372.springbootjwtjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sc372.springbootjwtjava.config.JwtTokenUtil;
import sc372.springbootjwtjava.domain.ApiResponse;
import sc372.springbootjwtjava.domain.AuthToken;
import sc372.springbootjwtjava.domain.User;
import sc372.springbootjwtjava.dto.SignInDto;
import sc372.springbootjwtjava.dto.UserDto;
// import sc372.springbootjwtjava.service.SecurityService;
import sc372.springbootjwtjava.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:5394")
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    // @Autowired
    // private SecurityService securityService;

    @PostMapping("/sign-up")
    public ApiResponse<User> signUp(@RequestBody UserDto userDto) {
        User user = userService.createUser(userDto);

        // securityService.autoLogin(user.getUserEmail(), user.getPassword());

        return new ApiResponse<>(HttpStatus.OK.value(), "사용자 생성에 성공하였습니다.", user);
    }

    @PostMapping("/sign-in")
    public ApiResponse<AuthToken> signIn(@RequestBody SignInDto signInDto) throws AuthenticationException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInDto.getUserEmail(), signInDto.getPassword()));
        final User user = userService.selectUserByEmail(signInDto.getUserEmail());
        final String token = jwtTokenUtil.generatToken(user);
        return new ApiResponse<>(HttpStatus.OK.value(), "로그인에 성공하였습니다.", new AuthToken(token, signInDto.getUserEmail()));
    }
}
