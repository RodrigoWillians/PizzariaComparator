package com.univesp.pi.pizzariacomparator.DTO.Pizzaria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PizzariaDTO {

    private String nome;
    private String CEP;
    private String endereco;
    private String site;
    private String telefone;
    private Double avaliacao;
}
