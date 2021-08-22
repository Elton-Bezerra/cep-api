package br.com.boroco.cepapi.core.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Async;

import br.com.boroco.cepapi.core.entities.EnderecoModel;
import br.com.boroco.cepapi.core.ports.CEPRepository;
import br.com.boroco.cepapi.core.ports.CepService;
import br.com.boroco.cepapi.core.ports.RemoteCEPService;

public class CepServiceImpl implements CepService {
	
	private CEPRepository repository;
	private RemoteCEPService remoteCepService;
	
	private Logger logger = LoggerFactory.getLogger(CepServiceImpl.class);


	public CepServiceImpl(CEPRepository repository, RemoteCEPService remoteCepService) {
		this.repository = repository;
		this.remoteCepService = remoteCepService;
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

	@Async("threadPoolTaskExecutor")
	@Override
	@CacheEvict("ceps")
	synchronized public void buscarCepRemotamente(String cep) {
		Optional<EnderecoModel> buscaRemota = remoteCepService.buscaRemota(cep);
		
		
		if(buscaRemota.isPresent()) {
			logger.info(String.format("Atualizando CEP %s", cep));
			EnderecoModel enderecoModel = buscaRemota.get();
			enderecoModel.setCep(enderecoModel.getCep().replace("-", ""));
			repository.save(buscaRemota.get());
		}
	}
	
	
	

}
