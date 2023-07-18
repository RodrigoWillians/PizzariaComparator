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

import com.univesp.pi.pizzariacomparator.DTO.Pizza.PizzaDTO;
import com.univesp.pi.pizzariacomparator.Model.Pizza;
import com.univesp.pi.pizzariacomparator.Service.PizzaService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping(value = "/v1/api/pizza")
@SecurityRequirement(name = "bearerAuth")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;
    

    @PreAuthorize("hasRole('ROLE_DEMONIO')")
    @GetMapping
    public ResponseEntity<List<Pizza>> buscarTodasPizzas() {
        List<Pizza> listaPizzas = pizzaService.buscarTodasPizzas();
        return ResponseEntity.ok(listaPizzas);
    }

    @PreAuthorize("hasRole('ROLE_MASTER')")
    @GetMapping("/buscarPorPaginas")
    public ResponseEntity<Page<Pizza>> buscarPizzaPaginado (@RequestParam(defaultValue = "0") Integer numeroPaginas) {
        Page<Pizza> paginas = pizzaService.buscarPizzaPaginado(numeroPaginas);
        return ResponseEntity.ok(paginas);
    }

    @PreAuthorize("hasRole('ROLE_DEMONIO')")
    @GetMapping("/buscarPorNomesParecidos")
    public ResponseEntity<List<Pizza>> buscarPizzaPorNomesParecidos(@RequestParam("nome") String nome) {
        List<Pizza> buscarNome = pizzaService.buscarPizzaPorNomesParecidos(nome);
        return ResponseEntity.ok(buscarNome);
    }

    //@PreAuthorize("hasRole('MASTER')")
    @GetMapping("/buscarPorNome")
    public ResponseEntity<Pizza> buscarPizzaPorNome(@RequestParam("nome") String nome) {
        Pizza buscarNome = pizzaService.buscarPizzaPorNome(nome);
        return ResponseEntity.ok(buscarNome);
    }

    //@PreAuthorize("hasRole('DEMONIO')")
    @GetMapping("/{id}")
    public ResponseEntity<Pizza> buscarPizzaPorId(@PathVariable UUID id) {
        Pizza pizza = pizzaService.buscarPizzaPorId(id);
            return ResponseEntity.ok(pizza);
    }

    @PreAuthorize("hasRole('ROLE_MASTER')")
    @PostMapping
    public ResponseEntity<Pizza> salvarPizza (@RequestBody @Valid PizzaDTO pizzaDTO) {

        return ResponseEntity.status(201).body(pizzaService.salvarPizza(pizzaDTO));
    }

    //@PreAuthorize("hasRole('MASTER')")
    @PutMapping("/{id}")
    public ResponseEntity<String> atulizarPizza(@PathVariable UUID id, @RequestBody PizzaDTO atualizar) {
        pizzaService.atualizarPizza(id, atualizar);
        return ResponseEntity.ok("Dados atualizados com sucesso");
    }

    //@PreAuthorize("hasRole('MASTER')")
    @PatchMapping("/{id}")
    public ResponseEntity<String> atulizarPizzaPatch(@PathVariable UUID id, @RequestBody PizzaDTO atualizarPatch) {
        pizzaService.atualizarPizzaPatch(id, atualizarPatch);
        return ResponseEntity.ok("Dados atualizados com sucesso");
    }

    //@PreAuthorize("hasRole('MASTER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPizza(@PathVariable UUID id) {
        pizzaService.deletarPizza(id);
        return ResponseEntity.ok("Pizza excluída com sucesso");
    }


}
