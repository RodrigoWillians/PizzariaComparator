package com.univesp.pi.pizzariacomparator.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.univesp.pi.pizzariacomparator.Model.Usuario;
import com.univesp.pi.pizzariacomparator.Repository.UsuarioRepository;

@Service
@Transactional
public class UserDetalsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByemail(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario " + username + " nao encontrado"));
        return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, usuario.getAuthorities());
    }
    
}