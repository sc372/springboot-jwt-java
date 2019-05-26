package io.sc372.springbootjwtjava.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthToken {
    private String token;
    private String userEmail;

    public AuthToken(){}
    public AuthToken(String token, String userEmail){
        this.token = token;
        this.userEmail = userEmail;
    }
    public AuthToken(String token){
        this.token = token;
    }
}