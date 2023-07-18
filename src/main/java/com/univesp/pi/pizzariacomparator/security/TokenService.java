package com.univesp.pi.pizzariacomparator.security;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;


import org.springframework.stereotype.Service;
import com.univesp.pi.pizzariacomparator.Model.Usuario;

@Service
public class TokenService {
    
    public String gerarToken(Usuario usuario) {
        
        return JWT.create()
                .withIssuer("Pizzaria")
                .withSubject(usuario.getUsername())
                .withClaim("id", usuario.getId().toString())
                .withExpiresAt(LocalDateTime.now()
                        .plusMinutes(10)
                        .toInstant(ZoneOffset.of("-03:00"))
                ).sign(Algorithm.HMAC256("secreta"));
    }

    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256("secreta"))
                .withIssuer("Pizzaria")
                .build().verify(token).getSubject();
    }
}