package com.example.springsecurity.repo;

import com.example.springsecurity.domain.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepo extends JpaRepository<Roles,Long> {
    Roles findByName(String name);
}
