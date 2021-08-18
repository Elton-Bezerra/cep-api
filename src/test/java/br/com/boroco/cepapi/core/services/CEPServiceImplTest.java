package br.com.boroco.cepapi.core.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.boroco.cepapi.core.entities.EnderecoModel;
import br.com.boroco.cepapi.core.ports.CEPRepository;
import br.com.boroco.cepapi.core.ports.CepService;

@ExtendWith(MockitoExtension.class)
public class CEPServiceImplTest {
	
	@Mock
	private static CEPRepository repository;
	
	@InjectMocks
	private CepServiceImpl service;
	
	@BeforeAll
	private static void setup() {
		repository = Mockito.mock(CEPRepository.class); 
	}
	
	private void mockarCepValido() {
		Mockito.when(repository.findByCep("03448000")).thenReturn(
				Optional.ofNullable(
						new EnderecoModel("03448000", "Rua João Domingues Oliveira", "Vila Carrão", "São Paulo", "SP")
					)
			);
	}
	

	@Test
	void buscaUmEnderecoCompletoValido() {
		mockarCepValido();
		
		Optional<EnderecoModel> enderecoOpt = service.busca("03448000");
		assertTrue(enderecoOpt.isPresent());
		
		EnderecoModel endereco = enderecoOpt.get();
		assertEquals("SP", endereco.getUf());
		assertEquals("São Paulo", endereco.getLocalidade());
		assertEquals("Vila Carrão", endereco.getBairro());
		assertEquals("Rua João Domingues Oliveira", endereco.getLogradouro());
		assertEquals("03448000", endereco.getCep());
		
	}
	
	@Test
	void buscaUmEnderecoInvalido() {
		Optional<EnderecoModel> enderecoOpt = service.busca("99999999");
		assertTrue(enderecoOpt.isEmpty());
	}
	
	void buscaUmEnderecoValidoIncompleto() {
		mockarCepAvenida();
		Optional<EnderecoModel> enderecoOpt = service.busca("03585011");
		
		assertTrue(enderecoOpt.isPresent());
		
		EnderecoModel endereco = enderecoOpt.get();
		
		assertEquals("03585010", endereco.getCep());
		assertEquals("Rua Alfeneiro", endereco.getLogradouro());
		
	}

	private void mockarCepAvenida() {
		Mockito.when(repository.findByCep("03585011")).thenReturn(
				Optional.ofNullable(
						new EnderecoModel("03585010", "Rua Alfeneiro", "Jardim Brasília (Zona Leste)", "São Paulo", "SP")
					)
			);
	}
	
	
}
