package br.com.boroco.cepapi.core.ports;

import java.util.Optional;

import br.com.boroco.cepapi.core.entities.EnderecoModel;

public interface CEPRepository {
	
	EnderecoModel save(EnderecoModel model);
	Optional<EnderecoModel> findByCep(String cep);
	
}
