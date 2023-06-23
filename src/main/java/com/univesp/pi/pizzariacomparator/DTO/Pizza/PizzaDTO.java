package com.univesp.pi.pizzariacomparator.DTO.Pizza;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PizzaDTO {
    
    private String nome;
    private String categoria;
    private String descricao;
    private String urlImagem;

    
    public UUID getId() {
        return null;
    }
}
