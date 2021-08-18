package br.com.boroco.cepapi.core.services;

import br.com.boroco.cepapi.core.entities.EnderecoModel;
import br.com.boroco.cepapi.core.ports.CEPRepository;
import br.com.boroco.cepapi.core.ports.CepService;

public class CepServiceImpl implements CepService {
	
	private CEPRepository repository;

	public CepServiceImpl(CEPRepository repository) {
		this.repository = repository;
	}

	@Override
	public EnderecoModel busca(String cep) {
		
		return repository.findByCep(cep);
	}

}
