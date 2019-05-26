package io.sc372.springbootjwtjava.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInDto {
    private String userEmail;
    private String password;
}