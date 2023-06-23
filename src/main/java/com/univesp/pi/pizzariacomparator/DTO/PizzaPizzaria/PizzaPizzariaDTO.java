package com.univesp.pi.pizzariacomparator.DTO.PizzaPizzaria;

import java.math.BigDecimal;

import com.univesp.pi.pizzariacomparator.DTO.Pizza.PizzaDTOId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PizzaPizzariaDTO {


    private PizzaDTOId pizza;
    private BigDecimal Preco;

}
