package com.univesp.pi.pizzariacomparator.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.univesp.pi.pizzariacomparator.DTO.Role.RoleDTO;
import com.univesp.pi.pizzariacomparator.Exceptions.ResourceNotFoundException;
import com.univesp.pi.pizzariacomparator.Model.Roles;
import com.univesp.pi.pizzariacomparator.Repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ModelMapper roleMapper;

    // GET ALL
    public List<Roles> buscarTodasRoles() {
        return roleRepository.findAll();
    }

    // GET BY NAME
    public Roles buscarPorNome(String nome) {
        return roleRepository.findByRoleName(nome.toString())
            .orElseThrow(() -> new ResourceNotFoundException("Role não encontrada: " + nome));
    }

    // GET BY ID
    private Roles buscarRolePorId(UUID id) {
        Optional<Roles> roleOptional = roleRepository.findById(id);
        return roleOptional.orElseThrow(() -> new ResourceNotFoundException("Não foi possível encontrar a role com o ID fornecido."));
    }

    //POST MAPPING
    public Roles salvarRole(RoleDTO roleDTO) {
        Roles roleSalvar = new Roles();
        roleMapper.map(roleDTO, roleSalvar); 
        return roleRepository.save(roleSalvar);
    }

    // DELETE MAPPING
    public void deletarRole(UUID id) {
        Roles roleDeletar = buscarRolePorId(id);
        roleRepository.delete(roleDeletar);
    }
}
