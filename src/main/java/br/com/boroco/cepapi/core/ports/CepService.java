package br.com.boroco.cepapi.core.ports;

import br.com.boroco.cepapi.core.entities.EnderecoModel;

public interface CepService {
	
	EnderecoModel busca(String cep);

}
