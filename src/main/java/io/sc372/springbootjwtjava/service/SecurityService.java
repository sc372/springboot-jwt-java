package io.sc372.springbootjwtjava.service;

public interface SecurityService {
    String findLoggedInUserEmail();
    Boolean autoLogin(String userEmail, String password);
}