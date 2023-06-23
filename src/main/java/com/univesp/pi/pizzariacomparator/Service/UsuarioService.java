package com.univesp.pi.pizzariacomparator.Service;

import org.modelmapper.ModelMapper;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.univesp.pi.pizzariacomparator.DTO.Usuario.UsuarioDTO;
import com.univesp.pi.pizzariacomparator.Exceptions.ResourceNotFoundException;
import com.univesp.pi.pizzariacomparator.Model.Roles;
import com.univesp.pi.pizzariacomparator.Model.Usuario;
import com.univesp.pi.pizzariacomparator.Repository.UsuarioRepository;


@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ModelMapper usuarioMapper;
    @Autowired
    private RoleService roleService;

    // PAGINAÇÃO 
    public Page<Usuario> buscarUsuarioPaginado(Integer numeroPaginas) {
        PageRequest paginaUsuario = PageRequest.of(numeroPaginas, 10);
        return usuarioRepository.findAll(paginaUsuario);
    }

    // GET ALL
    public List<Usuario> buscarTodasUsuarios() {
        return usuarioRepository.findAll();
    }

        // GET BY NAME
    public List<Usuario> buscarUsuarioPorNome(String nome) {
        return usuarioRepository.findByNome(nome);
    }

    // GET BY ID
    public Usuario buscarUsuarioPorId(UUID id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        return usuarioOptional.orElseThrow(() -> new ResourceNotFoundException("Não foi possível encontrar a usuario com o ID fornecido."));
    }

    //POST MAPPING
    public Usuario salvarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuarioSalvar = new Usuario();
        usuarioMapper.map(usuarioDTO, usuarioSalvar); 

        Roles role = roleService.buscarPorNome(usuarioDTO.getRole());
        if (role != null) {
            usuarioSalvar.setRoles(Collections.singletonList(role));
        }

        return usuarioRepository.save(usuarioSalvar);
    }

    // PUT MAPPING
    public Usuario atualizarUsuario(UUID id, UsuarioDTO atualizar) {
        Usuario usuarioAtualizar = buscarUsuarioPorId(id);
        usuarioMapper.map(atualizar, usuarioAtualizar);

        if (atualizar.getRole() != null) {
            Roles role = roleService.buscarPorNome(atualizar.getRole());
            if (role != null) {
                List<Roles> rolesList = new ArrayList<>();
                rolesList.add(role);
                usuarioAtualizar.setRoles(rolesList);
            }
        }

    return usuarioRepository.save(usuarioAtualizar);
}

    // PATCH MAPPING
    public Usuario atualizarUsuarioPatch(UUID id, UsuarioDTO atualizarPatch) {
        try {
            Usuario usuarioAtualizada = buscarUsuarioPorId(id);

            List<Field> fields = Arrays.asList(UsuarioDTO.class.getDeclaredFields());
            for (Field field : fields) {
                field.setAccessible(true);
                Object usuarioTemporaria = field.get(atualizarPatch);
                if (Objects.nonNull(usuarioTemporaria)) {
                    if (field.getName().equals("role")) {
                        Roles role = roleService.buscarPorNome(usuarioTemporaria.toString());
                        if (role != null) {
                            List<Roles> rolesList = new ArrayList<>();
                            rolesList.add(role);
                            usuarioAtualizada.setRoles(rolesList);
                        } 
                    } else {
                        Field usuarioField = Usuario.class.getDeclaredField(field.getName());
                        usuarioField.setAccessible(true);
                        usuarioField.set(usuarioAtualizada, usuarioTemporaria);
                    }
                }
            }
            return usuarioRepository.save(usuarioAtualizada);

        } catch (Exception e) {
            throw new ResourceNotFoundException("Ocorreu um erro durante a atualização dos dados do usuário.");
        }
    }

    // DELETE MAPPING
    public void deletarUsuario(UUID id) {
        Usuario usuarioDeletar = buscarUsuarioPorId(id);
        usuarioRepository.delete(usuarioDeletar);
    }
}


