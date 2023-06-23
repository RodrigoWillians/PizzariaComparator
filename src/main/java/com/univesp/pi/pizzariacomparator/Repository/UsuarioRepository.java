package com.univesp.pi.pizzariacomparator.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.univesp.pi.pizzariacomparator.Model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID>{
    
    Optional<Usuario> findByemail(String username);

    @Query("SELECT p FROM Usuario p WHERE p.nome = :nome")
    List<Usuario> findByNome(@Param("nome") String nome);
}
