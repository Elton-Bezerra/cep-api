package br.com.boroco.cepapi.core.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.boroco.cepapi.core.entities.EnderecoModel;
import br.com.boroco.cepapi.core.ports.CepService;

public class CEPServiceTest {

	@Test
	void buscaUmEnderecoCompletoValido() {
		CepService cepService = new CepServiceImpl(null);
		
		EnderecoModel endereco = cepService.busca("03448000");
		
		assertEquals("SP", endereco.getUf());
		assertEquals("São Paulo", endereco.getLocalidade());
		assertEquals("Vila Carrão", endereco.getBairro());
		assertEquals("Rua João Domingues Oliveira", endereco.getLogradouro());
		assertEquals("03448000", endereco.getCep());
		
	}
	
	
}
