package sc372.springbootjwtjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sc372.springbootjwtjava.domain.User;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUserEmail(String userEmail);
}