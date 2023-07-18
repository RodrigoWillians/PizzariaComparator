package com.univesp.pi.pizzariacomparator.Controller;

import javax.validation.Valid;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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

import com.univesp.pi.pizzariacomparator.DTO.Pizza.PizzaDTO;
import com.univesp.pi.pizzariacomparator.DTO.PizzaPizzaria.PizzaPizzariaDTO;
import com.univesp.pi.pizzariacomparator.DTO.PizzaPizzaria.PizzaPizzariaDTOId;
import com.univesp.pi.pizzariacomparator.Model.Pizza;
import com.univesp.pi.pizzariacomparator.Model.PizzaPizzaria;
import com.univesp.pi.pizzariacomparator.Service.PizzaPizzariaService;
import com.univesp.pi.pizzariacomparator.Service.PizzaService;


@RestController
@RequestMapping(value = "/v1/api/pizzaPizzaria")
public class PizzaPizzariaController {

    @Autowired
    private PizzaPizzariaService pizzaPizzariaService;

    @Autowired PizzaService pizzaService;
    

    //@PreAuthorize("hasRole('MASTER')")
    @GetMapping
    public ResponseEntity<List<PizzaPizzaria>> buscarTodasPizzaPizzarias() {
        List<PizzaPizzaria> listaPizzaPizzarias = pizzaPizzariaService.buscarTodasPizzaPizzarias();
        return ResponseEntity.ok(listaPizzaPizzarias);
    }

    //@PreAuthorize("hasRole('MASTER')")
    @GetMapping("/buscarPorPaginas")
    public ResponseEntity<Page<PizzaPizzaria>> buscarPizzaPizzariaPaginado (@RequestParam(defaultValue = "0") Integer numeroPaginas) {
        Page<PizzaPizzaria> paginas = pizzaPizzariaService.buscarPizzaPizzariaPaginado(numeroPaginas);
        return ResponseEntity.ok(paginas);
    }

    //@PreAuthorize("hasRole('MASTER')")
    @GetMapping("/compararprecos")
    public ResponseEntity<List<PizzaPizzaria>> compararPizzarias(@RequestParam("nome") String nome) {
        List<PizzaPizzaria> buscarPizzarias =  pizzaPizzariaService.compararPrecosPizza(nome);
        return ResponseEntity.ok(buscarPizzarias);
    }

    //@PreAuthorize("hasRole('MASTER')")
    @GetMapping("/{id}")
    public ResponseEntity<PizzaPizzaria> buscarPizzaPizzariaPorId(@PathVariable UUID id) {
        PizzaPizzaria pizzaPizzaPizzariaria = pizzaPizzariaService.buscarPizzaPizzariaPorId(id);
            return ResponseEntity.ok(pizzaPizzaPizzariaria);
    }

    //@PreAuthorize("hasRole('MASTER')")
    @PostMapping("/{pizzariaId}/pizzas")
    public ResponseEntity<List<PizzaPizzaria>> salvarPizzasPizzaria(@PathVariable("pizzariaId") UUID pizzariaId, @RequestBody List<PizzaPizzariaDTO> pizzaPizzariaDTOs) {
        PizzaPizzariaDTOId pizzariaDTOId = new PizzaPizzariaDTOId(pizzariaId);
        List<PizzaPizzaria> pizzasSalvas = pizzaPizzariaService.salvarPizzasPizzaria(pizzariaDTOId, pizzaPizzariaDTOs);
        return ResponseEntity.status(HttpStatus.CREATED).body(pizzasSalvas);
    }

    // public ResponseEntity<PizzaPizzaria> salvarPizzaPizzaria (@RequestBody @Valid PizzaPizzariaDTO pizzaPizzaPizzariariaDTO) {

    //     return ResponseEntity.status(201).body(pizzaPizzariariaService.salvarPizzaPizzaria(pizzaPizzaPizzariariaDTO));
    // }

    // @PutMapping("/{id}")
    // public ResponseEntity<String> atulizarPizzaPizzaria(@PathVariable UUID id, @RequestBody PizzaPizzariaDTO atualizar) {
    //     pizzaPizzariaService.atualizarPizzaPizzaria(id, atualizar);
    //     return ResponseEntity.ok("Dados atualizados com sucesso");
    // }

    // @PatchMapping("/{id}")
    // public ResponseEntity<String> atulizarPizzaPizzariaPatch(@PathVariable UUID id, @RequestBody PizzaPizzariaDTO atualizarPatch) {
    //     pizzaPizzariaService.atualizarPizzaPizzariaPatch(id, atualizarPatch);
    //     return ResponseEntity.ok("Dados atualizados com sucesso");
    // }

    //@PreAuthorize("hasRole('MASTER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPizzaPizzaria(@PathVariable UUID id) {
        pizzaPizzariaService.deletarPizzaPizzaria(id);
        return ResponseEntity.ok("PizzaPizzaria excluída com sucesso");
    }


}
