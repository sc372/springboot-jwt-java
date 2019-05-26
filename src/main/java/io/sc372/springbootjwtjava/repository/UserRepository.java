package io.sc372.springbootjwtjava.repository;

// import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.sc372.springbootjwtjava.domain.User;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUserEmail(String userEmail);
}