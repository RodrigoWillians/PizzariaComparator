package com.univesp.pi.pizzariacomparator.Service;

import org.modelmapper.ModelMapper;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.univesp.pi.pizzariacomparator.DTO.PizzaPizzaria.PizzaPizzariaDTO;
import com.univesp.pi.pizzariacomparator.DTO.PizzaPizzaria.PizzaPizzariaDTOId;
import com.univesp.pi.pizzariacomparator.Exceptions.ResourceNotFoundException;
import com.univesp.pi.pizzariacomparator.Model.Pizza;
import com.univesp.pi.pizzariacomparator.Model.PizzaPizzaria;
import com.univesp.pi.pizzariacomparator.Model.Pizzaria;
import com.univesp.pi.pizzariacomparator.Repository.PizzaPizzariaRepository;


@Service
public class PizzaPizzariaService {

    @Autowired
    private PizzaPizzariaRepository pizzaPizzariaRepository;
    @Autowired
    private ModelMapper pizzaPizzariaMapper;
    @Autowired
    private PizzaService pizzaService;
    @Autowired
    private PizzariaService pizzariaService;


    // PAGINAÇÃO 
    public Page<PizzaPizzaria> buscarPizzaPizzariaPaginado(Integer numeroPaginas) {
        PageRequest paginaPizzaPizzaria = PageRequest.of(numeroPaginas, 10);
        return pizzaPizzariaRepository.findAll(paginaPizzaPizzaria);
    }

    // GET ALL
    public List<PizzaPizzaria> buscarTodasPizzaPizzarias() {
        return pizzaPizzariaRepository.findAll();
    }

    // GET BY ID
    public PizzaPizzaria buscarPizzaPizzariaPorId(UUID id) {
        Optional<PizzaPizzaria> pizzaPizzariaOptional = pizzaPizzariaRepository.findById(id);
        return pizzaPizzariaOptional.orElseThrow(() -> new ResourceNotFoundException("Não foi possível encontrar a pizzaPizzaria com o ID fornecido."));
    }

    // GET COMPARAR PRECOS
    public List<PizzaPizzaria> compararPrecosPizza(String nomePizza){
        Pizza pizza = pizzaService.buscarPizzaPorNome(nomePizza);
        List<PizzaPizzaria> pizzarias = pizzaPizzariaRepository.findByPizzariasParecidas(pizza.getId());
        Collections.sort(pizzarias, Comparator.comparing(PizzaPizzaria::getPreco)); 
        return pizzarias; // retorna a lista ordenada
    }

    //POST MAPPING  
    public List<PizzaPizzaria> salvarPizzasPizzaria(PizzaPizzariaDTOId pizzariaId, List<PizzaPizzariaDTO> pizzaPizzariaDTOs) {
        UUID pizzariaUuid = pizzariaId.getPizzaria().getId();
        Pizzaria pizzaria = pizzariaService.buscarPizzariaPorId(pizzariaUuid);
    
        List<PizzaPizzaria> pizzasSalvas = new ArrayList<>();
        Set<UUID> pizzasCadastradas = new HashSet<>();
    
        for (PizzaPizzariaDTO pizzaPizzariaDTO : pizzaPizzariaDTOs) {
            UUID pizzaId = pizzaPizzariaDTO.getPizza().getId();
            BigDecimal preco = pizzaPizzariaDTO.getPreco();
    
            // Verifica se a pizza já foi cadastrada anteriormente
            if (pizzasCadastradas.contains(pizzaId)) {
                throw new IllegalArgumentException("A pizza de ID '" + pizzaId + "' já foi cadastrada para a pizzaria '" + pizzaria.getNome() + "'.");
            }
    
            Pizza pizza = pizzaService.buscarPizzaPorId(pizzaId);
    
            PizzaPizzaria pizzaPizzariaSalvar = new PizzaPizzaria();
            pizzaPizzariaSalvar.setPizza(pizza);
            pizzaPizzariaSalvar.setPizzaria(pizzaria);
            pizzaPizzariaSalvar.setPreco(preco);
    
            pizzasSalvas.add(pizzaPizzariaSalvar);
            pizzasCadastradas.add(pizzaId);
        }
    
        return pizzaPizzariaRepository.saveAll(pizzasSalvas);
    }

    // PUT MAPPING
    // public PizzaPizzaria atualizarPizzaPizzaria(UUID id, PizzaPizzariaDTO atualizar) {
    //     PizzaPizzaria pizzaPizzariaAtualizar = buscarPizzaPizzariaPorId(id);
    //     pizzaService.buscarPizzaPorId(atualizar.getPizza().getId());
    //     pizzariaService.buscarPizzariaPorId(atualizar.getPizzaria().getId());

    //     pizzaPizzariaMapper.map(atualizar, pizzaPizzariaAtualizar);    
    //     return pizzaPizzariaRepository.save(pizzaPizzariaAtualizar);
    // }

    // PATCH MAPPING
    // public PizzaPizzaria atualizarPizzaPizzariaPatch(PizzaPizzariaDTOId pizzariaId, PizzaPizzariaDTO atualizarPatch) {
    //     try {
    //         UUID pizzariaUuid = pizzariaId.getPizzaria().getId();
    //         Pizzaria pizzaPizzariaAtualizada = pizzariaService.buscarPizzariaPorId(pizzariaUuid);
    //         Field[] fields = PizzaPizzariaDTO.class.getDeclaredFields();
    //         for (Field field : fields) {
    //             field.setAccessible(true);
    //             Object pizzaPizzariaTemporaria = field.get(atualizarPatch);
    //             if (pizzaPizzariaTemporaria != null) {
    //                 try {
    //                     Field pizzaPizzariaField = PizzaPizzaria.class.getDeclaredField(field.getName());
    //                     pizzaPizzariaField.setAccessible(true);
    //                     pizzaPizzariaField.set(pizzaPizzariaAtualizada, pizzaPizzariaTemporaria);
    //                 } catch (NoSuchFieldException e) {
    //                     // ignorar campos que não existem em PizzaPizzaria
    //                 }
    //             }
    //         }
    //         return pizzaPizzariaRepository.save(pizzaPizzariaAtualizada);
    //     } catch (IllegalAccessException | IllegalArgumentException | SecurityException e) {
    //         throw new ResourceNotFoundException("Ocorreu um erro durante a atualização dos dados da pizzaPizzaria.");
    //     }
    // }

    public void deletarPizzaPizzaria(UUID id) {
        PizzaPizzaria pizzaPizzariaDeletar = buscarPizzaPizzariaPorId(id);
        pizzaPizzariaRepository.delete(pizzaPizzariaDeletar);
    }
}

