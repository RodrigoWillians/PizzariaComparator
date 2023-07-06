package com.univesp.pi.pizzariacomparator.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.univesp.pi.pizzariacomparator.Model.Pizzaria;
import com.univesp.pi.pizzariacomparator.Model.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Roles, UUID>{

    @Query("SELECT r FROM Roles r WHERE r.roleName = :roleName")
    Optional<Roles> findByRoleName(@Param("roleName") String roleName);

    //Optional<Roles> findByRoleName(String roleName);
}
