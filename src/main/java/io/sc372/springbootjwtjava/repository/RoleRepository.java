package io.sc372.springbootjwtjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.sc372.springbootjwtjava.domain.Role;

public interface RoleRepository extends JpaRepository<Role, String>{}