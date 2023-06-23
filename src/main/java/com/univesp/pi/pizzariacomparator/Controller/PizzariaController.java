package com.univesp.pi.pizzariacomparator.Controller;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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

import com.univesp.pi.pizzariacomparator.DTO.Pizzaria.PizzariaDTO;
import com.univesp.pi.pizzariacomparator.Model.Pizzaria;
import com.univesp.pi.pizzariacomparator.Service.PizzariaService;


@RestController
@RequestMapping(value = "/v1/api/pizzaria")
public class PizzariaController {
    @Autowired
    private PizzariaService pizzariaService;
    

    @GetMapping
    public ResponseEntity<List<Pizzaria>> buscarTodasPizzarias() {
        List<Pizzaria> listaPizzaria = pizzariaService.buscarTodasPizzarias();
        return ResponseEntity.ok(listaPizzaria);
    }

    @GetMapping("/buscarPorPaginas")
    public ResponseEntity<Page<Pizzaria>> buscarPizzariaPaginado (@RequestParam(defaultValue = "0") Integer numeroPaginas) {
        Page<Pizzaria> paginas = pizzariaService.buscarPizzariaPaginado(numeroPaginas);
        return ResponseEntity.ok(paginas);
        }

    @GetMapping("/buscarPorNome")
    public ResponseEntity<List<Pizzaria>> buscarPizzariaPorNome(@RequestParam("nome") String nome) {
        List<Pizzaria> buscarNome = pizzariaService.buscarPizzariaPorNome(nome);
        return ResponseEntity.ok(buscarNome);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizzaria> buscarPizzariaPorId(@PathVariable UUID id) {
        Pizzaria pizzaria = pizzariaService.buscarPizzariaPorId(id);
            return ResponseEntity.ok(pizzaria);
        }

    @PostMapping
    public ResponseEntity<Pizzaria> salvarPizzaria (@RequestBody @Valid PizzariaDTO pizzariaDTO) {

        return ResponseEntity.status(201).body(pizzariaService.salvarPizzaria(pizzariaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atulizarPizzaria(@PathVariable UUID id, @RequestBody PizzariaDTO atualizar) {
        pizzariaService.atualizarPizzaria(id, atualizar);
        return ResponseEntity.ok("Dados atualizados com sucesso");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> atulizarPizzariaPatch(@PathVariable UUID id, @RequestBody PizzariaDTO atualizarPatch) {
        pizzariaService.atualizarPizzariaPatch(id, atualizarPatch);
        return ResponseEntity.ok("Dados atualizados com sucesso");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPizzaria(@PathVariable UUID id) {
        pizzariaService.deletarPizzaria(id);
        return ResponseEntity.ok("Pizzaria excluída com sucesso");
    }


}

