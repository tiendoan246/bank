package com.bank.authenticationserver.bankauthentication.repository;

import com.bank.authenticationserver.bankauthentication.model.UserRoleEnum;
import com.bank.authenticationserver.bankauthentication.repository.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(UserRoleEnum name);
}
