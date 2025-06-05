package com.demoproject.JwtAuth.repository;


import com.demoproject.JwtAuth.entities.Role;
import com.demoproject.JwtAuth.entities.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(RoleEnum name);
}
