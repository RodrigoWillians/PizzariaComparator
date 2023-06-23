package com.univesp.pi.pizzariacomparator.Model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_pizzaria")
public class Pizzaria {

    @Id 
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(columnDefinition = "varbinary(36)")
    private UUID id;

    @Size(max = 25)
    @Column(nullable = false)
    @NotBlank
    private String nome;

    @Size(max = 10)
    @Column(nullable = false)
    @NotBlank
    private String CEP;

    @Size(max = 100)
    @Column(nullable = false)
    @NotBlank
    private String endereco;

    @Size(max = 100)
    @Column(nullable = false)
    @NotBlank
    private String site;

    @Size(max = 16)
    @Column(nullable = false)
    @NotBlank
    private String telefone;

    @Column(nullable = false)
    @NotBlank
    private Double avaliacao;
}
