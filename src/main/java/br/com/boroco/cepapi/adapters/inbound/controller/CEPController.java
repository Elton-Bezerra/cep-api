package br.com.boroco.cepapi.adapters.inbound.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.boroco.cepapi.core.entities.EnderecoModel;
import br.com.boroco.cepapi.core.ports.CepService;

@RestController
public class CEPController {
	
	@Autowired
	CepService cepService;
	
	@GetMapping(path="cep-api/busca/{cep}", produces = "application/json")
	public ResponseEntity<EnderecoModel> buscaPorCep(@PathVariable(name = "cep") String cep) {
		
		return ResponseEntity.ok(cepService.busca(cep));
	}

}
