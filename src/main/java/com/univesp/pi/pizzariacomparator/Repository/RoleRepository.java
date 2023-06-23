package com.univesp.pi.pizzariacomparator.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.univesp.pi.pizzariacomparator.Model.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Roles, UUID>{

    Optional<Roles> findByRoleName(String roleName);
}
