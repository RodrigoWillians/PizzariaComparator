package com.univesp.pi.pizzariacomparator.DTO.Usuario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private String nome;
    private String email;
    private String senha;
    private String role;
    
    public void setSenha(String senha) {
        this.senha = new BCryptPasswordEncoder().encode(senha);
    }
}
