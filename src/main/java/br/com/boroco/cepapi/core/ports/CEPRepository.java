package br.com.boroco.cepapi.core.ports;

import br.com.boroco.cepapi.core.entities.EnderecoModel;

public interface CEPRepository {
	
	EnderecoModel save(EnderecoModel model);
	EnderecoModel findByCep(String cep);
	
}
