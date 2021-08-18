package br.com.boroco.cepapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.boroco.cepapi.core.entities.EnderecoModel;
import br.com.boroco.cepapi.core.ports.CepService;
import br.com.boroco.cepapi.core.services.CepServiceImpl;

@SpringBootTest
class CEPApiApplicationTests {

	@Test
	void buscaUmEnderecoCompletoValido() {
		CepService cepService = new CepServiceImpl();
		
		EnderecoModel endereco = cepService.busca("03448000");
		
		assertEquals("SP", endereco.getUf());
		assertEquals("São Paulo", endereco.getLocalidade());
		assertEquals("Vila Carrão", endereco.getBairro());
		assertEquals("Rua João Domingues Oliveira", endereco.getLogradouro());
		assertEquals("03448000", endereco.getCep());
		
	}

}
