package com.univesp.pi.pizzariacomparator.DTO.Usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    private String email;
    private String senha;
    
    // public void setSenha(String senha) {
    //     this.senha = new BCryptPasswordEncoder().encode(senha);
    // }
}

