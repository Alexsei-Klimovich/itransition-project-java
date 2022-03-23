package com.alexsei.itransition.repository;

import com.alexsei.itransition.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findRoleByName(String name);
}
