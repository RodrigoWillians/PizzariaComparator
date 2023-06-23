package com.univesp.pi.pizzariacomparator.DTO.PizzaPizzaria;

import java.util.UUID;

import com.univesp.pi.pizzariacomparator.DTO.Pizzaria.PizzariaDTOId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PizzaPizzariaDTOId {

    private PizzariaDTOId pizzaria;

    
    public PizzaPizzariaDTOId(UUID pizzariaId) {
        this.pizzaria = new PizzariaDTOId(pizzariaId);
    }

    public PizzariaDTOId getPizzaria() {
        return pizzaria;
    }

    public void setPizzaria(PizzariaDTOId pizzaria) {
        this.pizzaria = pizzaria;
    }
}