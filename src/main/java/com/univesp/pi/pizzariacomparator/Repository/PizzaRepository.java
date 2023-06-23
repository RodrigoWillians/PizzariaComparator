package com.univesp.pi.pizzariacomparator.Repository;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.univesp.pi.pizzariacomparator.Model.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, UUID>{
    
    @Query("SELECT p FROM Pizza p WHERE p.nome LIKE %:nome%")
    List<Pizza> findByNomesParecidos(@Param("nome") String nome);

    @Query("SELECT p FROM Pizza p WHERE p.nome = :nome")
    Pizza findByNome(@Param("nome") String nome);

}
