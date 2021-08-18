package br.com.boroco.cepapi.core.ports;

import java.util.Optional;

import br.com.boroco.cepapi.core.entities.EnderecoModel;

public interface CepService {
	
	Optional<EnderecoModel> busca(String cep);

}
