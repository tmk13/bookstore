package com.apress.bookstore.repository;

import com.apress.bookstore.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findRoleByRoleName(String roleName);
}
