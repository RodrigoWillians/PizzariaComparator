package com.univesp.pi.pizzariacomparator.Controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.univesp.pi.pizzariacomparator.DTO.Role.RoleDTO;
import com.univesp.pi.pizzariacomparator.Model.Pizza;
import com.univesp.pi.pizzariacomparator.Model.Roles;
import com.univesp.pi.pizzariacomparator.Service.RoleService;

@RestController
@RequestMapping(value = "/v1/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;
    

    @GetMapping
    public ResponseEntity<List<Roles>> buscarTodasRoles() {
        List<Roles> listaRoles = roleService.buscarTodasRoles();
        return ResponseEntity.ok(listaRoles);
    }

    @GetMapping("/buscarPorNome")
    public ResponseEntity<Roles> buscarPorNome(@RequestParam("nome") String nome) {
        Roles buscarNome = roleService.buscarPorNome(nome);
        return ResponseEntity.ok(buscarNome);
    }

    @PostMapping
    public ResponseEntity<Roles> salvarRole (@RequestBody @Valid RoleDTO roleDTO) {

        return ResponseEntity.status(201).body(roleService.salvarRole(roleDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarRole(@PathVariable UUID id) {
        roleService.deletarRole(id);
        return ResponseEntity.ok("Role excluído com sucesso");
    }

}
