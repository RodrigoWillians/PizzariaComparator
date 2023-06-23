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

import com.univesp.pi.pizzariacomparator.DTO.Pizzaria.PizzariaDTO;
import com.univesp.pi.pizzariacomparator.Exceptions.ResourceNotFoundException;
import com.univesp.pi.pizzariacomparator.Model.Pizzaria;
import com.univesp.pi.pizzariacomparator.Repository.PizzariaRepository;

@Service
public class PizzariaService {
    

    @Autowired
    private PizzariaRepository pizzariaRepository;
    @Autowired
    private ModelMapper pizzariaMapper;

    // PAGINAÇÃO 
    public Page<Pizzaria> buscarPizzariaPaginado(Integer numeroPaginas) {
        PageRequest paginaPizzaria = PageRequest.of(numeroPaginas, 10);
        return pizzariaRepository.findAll(paginaPizzaria);
    }

    // GET ALL
    public List<Pizzaria> buscarTodasPizzarias() {
        return pizzariaRepository.findAll();
    }

        // GET BY NAME
    public List<Pizzaria> buscarPizzariaPorNome(String nome) {
        return pizzariaRepository.findByNome(nome);
      }

        // GET BY ID
        public Pizzaria buscarPizzariaPorId(UUID id) {
            Optional<Pizzaria> pizzariaOptional = pizzariaRepository.findById(id);
            return pizzariaOptional.orElseThrow(() -> new ResourceNotFoundException("Não foi possível encontrar a pizzaria com o ID fornecido."));
        }

        //POST MAPPING
        public Pizzaria salvarPizzaria(PizzariaDTO pizzariaDTO) {
        Pizzaria pizzariaSalvar = new Pizzaria();
        pizzariaMapper.map(pizzariaDTO, pizzariaSalvar); 
        return pizzariaRepository.save(pizzariaSalvar);
        }

        // PUT MAPPING
        public Pizzaria atualizarPizzaria(UUID id, PizzariaDTO atualizar) {
            Pizzaria pizzariaAtualizar = buscarPizzariaPorId(id);
            pizzariaMapper.map(atualizar, pizzariaAtualizar);    
            return pizzariaRepository.save(pizzariaAtualizar);
        }

        // PATCH MAPPING
    public Pizzaria atualizarPizzariaPatch(UUID id, PizzariaDTO atualizarPatch) {

        try {
            Pizzaria pizzariaAtualizada = buscarPizzariaPorId(id);
        
            List<Field> fields = Arrays.asList(PizzariaDTO.class.getDeclaredFields());
            for (Field field : fields) {
                field.setAccessible(true);
                Object pizzariaTemporaria = field.get(atualizarPatch);
                if (Objects.nonNull(pizzariaTemporaria)) {
                    Field pizzariaField = Pizzaria.class.getDeclaredField(field.getName());
                    pizzariaField.setAccessible(true);
                    pizzariaField.set(pizzariaAtualizada, pizzariaTemporaria);
                }
            }
            return pizzariaRepository.save(pizzariaAtualizada);

        } catch (Exception e) {
            throw new ResourceNotFoundException("Ocorreu um erro durante a atualização dos dados da pizza.");
        }
    }

    public void deletarPizzaria(UUID id) {
        Pizzaria pizzariaDeletar = buscarPizzariaPorId(id);
        pizzariaRepository.delete(pizzariaDeletar);
    }
}



