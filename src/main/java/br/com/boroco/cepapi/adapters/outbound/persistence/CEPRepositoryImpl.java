package br.com.boroco.cepapi.adapters.outbound.persistence;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.boroco.cepapi.core.entities.EnderecoModel;
import br.com.boroco.cepapi.core.ports.CEPRepository;

@Component
public class CEPRepositoryImpl implements CEPRepository {
	
    private final SpringCEPRepository enderecoRepository;

    public CEPRepositoryImpl(final SpringCEPRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }
    
	@Override
	public EnderecoModel save(EnderecoModel model) {
		return this.enderecoRepository.save(model);
	}

	@Override
	public Optional<EnderecoModel> findByCep(String cep) {
 		return this.enderecoRepository.findByCep(cep);
	}

}
