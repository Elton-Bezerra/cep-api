package br.com.boroco.cepapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import br.com.boroco.cepapi.adapters.inbound.controller.CEPController;
import br.com.boroco.cepapi.adapters.inbound.dto.DetailDTO;
import br.com.boroco.cepapi.core.entities.EnderecoModel;

@SpringBootTest
class CEPApiApplicationTests {

	@Autowired
	CEPController controller;
	
	@Test
	@SuppressWarnings("unchecked")
	public void buscaCepValido() {
		ResponseEntity<EnderecoModel> busca = (ResponseEntity<EnderecoModel>) controller.busca("03448000");
		
		EnderecoModel resposta = busca.getBody();
		
		assertEquals(200, busca.getStatusCode().value());
		assertEquals("Vila Carrão", resposta.getBairro());
	}
	
	@Test
	public void buscaCepInvalido() {
		assertThrows(ConstraintViolationException.class, () -> {
			controller.busca("0344800x");
		});
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void buscaCEPNaoEncontrado() {
		ResponseEntity<DetailDTO> busca = (ResponseEntity<DetailDTO>) controller.busca("03585010");
		
		DetailDTO resposta = busca.getBody();
		
		assertEquals(404, busca.getStatusCode().value());
		assertEquals("CEP 03585010 não encontrado", resposta.getMessage());
	}
}
