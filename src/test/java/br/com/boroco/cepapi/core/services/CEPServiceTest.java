package br.com.boroco.cepapi.core.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import br.com.boroco.cepapi.core.entities.EnderecoModel;
import br.com.boroco.cepapi.core.ports.CEPRepository;
import br.com.boroco.cepapi.core.ports.CepService;

public class CEPServiceTest {
	
	@Mock
	private static CEPRepository repository;
	
	private CepService service;
	
	@BeforeAll
	private static void setup() {
		repository = Mockito.mock(CEPRepository.class); 
	}
	
	private void setupCEPValido() {
		Mockito.when(repository.findByCep("03448000")).thenReturn(
				new EnderecoModel("03448000", "Rua João Domingues Oliveira", "Vila Carrão", "São Paulo", "SP")
			);
		
		service = new CepServiceImpl(repository);
	}
	

	@Test
	void buscaUmEnderecoCompletoValido() {
		setupCEPValido();
		
		EnderecoModel endereco = service.busca("03448000");
		
		assertNotNull(endereco);
		assertEquals("SP", endereco.getUf());
		assertEquals("São Paulo", endereco.getLocalidade());
		assertEquals("Vila Carrão", endereco.getBairro());
		assertEquals("Rua João Domingues Oliveira", endereco.getLogradouro());
		assertEquals("03448000", endereco.getCep());
		
	}
	
	
}
