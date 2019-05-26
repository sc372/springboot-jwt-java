package sc372.springbootjwtjava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sc372.springbootjwtjava.domain.Role;

public interface RoleRepository extends JpaRepository<Role, String>{
    List<Role> findByName(String name);
}