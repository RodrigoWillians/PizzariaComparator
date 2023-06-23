package com.univesp.pi.pizzariacomparator.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.univesp.pi.pizzariacomparator.Model.Pizzaria;

public interface PizzariaRepository extends JpaRepository<Pizzaria, UUID> {
    
    @Query("SELECT p FROM Pizzaria p WHERE p.nome = :nome")
    List<Pizzaria> findByNome(@Param("nome") String nome);
}
