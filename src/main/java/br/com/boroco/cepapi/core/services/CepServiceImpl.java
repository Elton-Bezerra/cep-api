package br.com.boroco.cepapi.core.services;

import br.com.boroco.cepapi.core.entities.EnderecoModel;
import br.com.boroco.cepapi.core.ports.CepService;

public class CepServiceImpl implements CepService {

	@Override
	public EnderecoModel busca(String cep) {
		return new EnderecoModel("03448000", "Rua João Domingues Oliveira", "Vila Carrão", "São Paulo", "SP");
	}

}
