package com.univesp.pi.pizzariacomparator.Controller;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.univesp.pi.pizzariacomparator.DTO.Usuario.UsuarioDTO;
import com.univesp.pi.pizzariacomparator.Model.Usuario;
import com.univesp.pi.pizzariacomparator.Service.UsuarioService;

//@PreAuthorize("hasRole('MASTER')")
@RestController
@RequestMapping(value = "/v1/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
    //@PreAuthorize("hasRole('MASTER')")
    @GetMapping
    public ResponseEntity<List<Usuario>> buscarTodasUsuarios() {
        List<Usuario> listaUsuarios = usuarioService.buscarTodasUsuarios();
        return ResponseEntity.ok(listaUsuarios);
    }

    @PreAuthorize("hasRole('ROLE_DEMONIO')")
    @GetMapping("/buscarPorPaginas")
    public ResponseEntity<Page<Usuario>> buscarUsuarioPaginado (@RequestParam(defaultValue = "0") Integer numeroPaginas) {
        Page<Usuario> paginas = usuarioService.buscarUsuarioPaginado(numeroPaginas);
        return ResponseEntity.ok(paginas);
        }

    @PreAuthorize("hasRole('ROLE_DEMONIO')")
    @GetMapping("/buscarPorNome")
    public ResponseEntity<List<Usuario>> buscarUsuarioPorNome(@RequestParam("nome") String nome) {
        List<Usuario> buscarNome = usuarioService.buscarUsuarioPorNome(nome);
        return ResponseEntity.ok(buscarNome);
    }

    @PreAuthorize("hasRole('MASTER')")
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable UUID id) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(id);
            return ResponseEntity.ok(usuario);
        }

    //@PreAuthorize("hasRole('MASTER')")
    @PostMapping
    public ResponseEntity<Usuario> salvarUsuario (@RequestBody @Valid UsuarioDTO usuarioDTO) {
        return ResponseEntity.status(201).body(usuarioService.salvarUsuario(usuarioDTO));
    }

    @PreAuthorize("hasRole('MASTER')")
    @PutMapping("/{id}")
    public ResponseEntity<String> atulizarUsuario(@PathVariable UUID id, @RequestBody UsuarioDTO atualizar) {
        usuarioService.atualizarUsuario(id, atualizar);
        return ResponseEntity.ok("Dados atualizados com sucesso");
    }

    @PreAuthorize("hasRole('MASTER')")
    @PatchMapping("/{id}")
    public ResponseEntity<String> atulizarUsuarioPatch(@PathVariable UUID id, @RequestBody UsuarioDTO atualizarPatch) {
        usuarioService.atualizarUsuarioPatch(id, atualizarPatch);
        return ResponseEntity.ok("Dados atualizados com sucesso");
    }

    @PreAuthorize("hasRole('MASTER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarUsuario(@PathVariable UUID id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.ok("Usuario excluído com sucesso");
    }


}
