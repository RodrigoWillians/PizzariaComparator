package com.univesp.pi.pizzariacomparator.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.univesp.pi.pizzariacomparator.Model.PizzaPizzaria;

public interface PizzaPizzariaRepository extends JpaRepository<PizzaPizzaria, UUID>{

    @Query("SELECT pp FROM PizzaPizzaria pp WHERE pizza_id = :id")
    List<PizzaPizzaria> findByPizzariasParecidas(@Param("id") UUID id);    

}
