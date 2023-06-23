package com.univesp.pi.pizzariacomparator.Service;

import org.modelmapper.ModelMapper;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.univesp.pi.pizzariacomparator.DTO.Pizza.PizzaDTO;

import com.univesp.pi.pizzariacomparator.Exceptions.ResourceNotFoundException;
import com.univesp.pi.pizzariacomparator.Model.Pizza;
import com.univesp.pi.pizzariacomparator.Repository.PizzaRepository;


@Service
public class PizzaService {
    
    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private ModelMapper pizzaMapper;

    // PAGINAÇÃO 
    public Page<Pizza> buscarPizzaPaginado(Integer numeroPaginas) {
        PageRequest paginaPizza = PageRequest.of(numeroPaginas, 10);
        return pizzaRepository.findAll(paginaPizza);
    }

    // GET ALL
    public List<Pizza> buscarTodasPizzas() {
        return pizzaRepository.findAll();
    }

        // GET BY NAME
    public List<Pizza> buscarPizzaPorNomesParecidos(String nome) {
        return pizzaRepository.findByNomesParecidos(nome);
      }

      public Pizza buscarPizzaPorNome(String nome) {
        return pizzaRepository.findByNome(nome);
      }

        // GET BY ID
        public Pizza buscarPizzaPorId(UUID id) {
            Optional<Pizza> pizzaOptional = pizzaRepository.findById(id);
            return pizzaOptional.orElseThrow(() -> new ResourceNotFoundException("A pizza com o ID " + id + " não foi encontrada."));
        }

        //POST MAPPING

    public Pizza salvarPizza(PizzaDTO pizzaDTO) {
        Pizza pizzaSalvar = new Pizza();
        pizzaMapper.map(pizzaDTO, pizzaSalvar); 
        return pizzaRepository.save(pizzaSalvar);
    }

        // PUT MAPPING
        public Pizza atualizarPizza(UUID id, PizzaDTO atualizar) {
            Pizza pizzaAtualizar = buscarPizzaPorId(id);
            pizzaMapper.map(atualizar, pizzaAtualizar);    
            return pizzaRepository.save(pizzaAtualizar);
        }

        // PATCH MAPPING
        public Pizza atualizarPizzaPatch(UUID id, PizzaDTO atualizarPatch) {

            try {
                Pizza pizzaAtualizada = buscarPizzaPorId(id);
            
                List<Field> fields = Arrays.asList(PizzaDTO.class.getDeclaredFields());
                for (Field field : fields) {
                    field.setAccessible(true);
                    Object pizzaTemporaria = field.get(atualizarPatch);
                    if (Objects.nonNull(pizzaTemporaria)) {
                        Field pizzaField = Pizza.class.getDeclaredField(field.getName());
                        pizzaField.setAccessible(true);
                        pizzaField.set(pizzaAtualizada, pizzaTemporaria);
                    }
                }
                return pizzaRepository.save(pizzaAtualizada);

            } catch (Exception e) {
                throw new ResourceNotFoundException("Ocorreu um erro durante a atualização dos dados da pizza.");
            }
        }

        public void deletarPizza(UUID id) {
            Pizza pizzaDeletar = buscarPizzaPorId(id);
            pizzaRepository.delete(pizzaDeletar);
        }
}


