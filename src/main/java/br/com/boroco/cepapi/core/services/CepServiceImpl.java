package br.com.boroco.cepapi.core.services;

import java.util.Optional;

import br.com.boroco.cepapi.core.entities.EnderecoModel;
import br.com.boroco.cepapi.core.ports.CEPRepository;
import br.com.boroco.cepapi.core.ports.CepService;

public class CepServiceImpl implements CepService {
	
	private CEPRepository repository;

	public CepServiceImpl(CEPRepository repository) {
		this.repository = repository;
	}

	@Override
	public Optional<EnderecoModel> busca(String cep) {
		Optional<EnderecoModel> enderecoOpt = repository.findByCep(cep);
		
		if(enderecoOpt.isPresent()) {
			return enderecoOpt;
		}
		
		int count = 1;
		while(enderecoOpt.isEmpty() && !cep.matches("(^[0]{8})$")) {
			cep = cep.substring(0, cep.length() - count) + "0".repeat(count++);
			enderecoOpt = repository.findByCep(cep);
		}
		
		return enderecoOpt;
	}

}
