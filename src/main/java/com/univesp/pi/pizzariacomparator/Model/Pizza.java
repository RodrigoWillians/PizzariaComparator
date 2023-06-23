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

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_pizza")
public class Pizza implements Serializable{

    @Id 
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(columnDefinition = "varbinary(36)")
    private UUID id;

    @Size(max = 25)
    @Column(nullable = false)
    @NotBlank
    private String nome;

    @Size(max = 25)
    @Column(nullable = false)
    @NotBlank
    private String categoria;

    @Size(max = 100)
    private String descricao;

    @Column(nullable = false)
    @NotBlank
    private String urlImagem;
}
