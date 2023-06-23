package com.univesp.pi.pizzariacomparator.DTO.Pizzaria;

import java.util.UUID;

import com.univesp.pi.pizzariacomparator.DTO.Pizza.PizzaDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PizzariaDTOId {

    private UUID id;

    public static PizzaDTO getPizzaria() {
        return null;
    }
}
