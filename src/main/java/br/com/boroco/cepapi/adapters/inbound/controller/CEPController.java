package br.com.boroco.cepapi.adapters.inbound.controller;

import java.util.Optional;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.boroco.cepapi.adapters.inbound.dto.DetailDTO;
import br.com.boroco.cepapi.core.entities.EnderecoModel;
import br.com.boroco.cepapi.core.ports.CepService;

@RestController
@Validated
public class CEPController {
	
	@Autowired
	CepService cepService;
	
    @ExceptionHandler({ ConstraintViolationException.class})
    public ResponseEntity<?> handleException(ConstraintViolationException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DetailDTO("CEP Inválido", ex.getMessage()));
    }
	
	@GetMapping(path="cep-api/busca/{cep}", produces = "application/json")
	public ResponseEntity<?> busca(
			@PathVariable(name = "cep") 
			@Size(min = 8, max = 8, message = "O CEP deve ter 8 caracteres")
			@Pattern(regexp = "(^[0-9]{8})", message = "Apenas caracteres númericos são permitidos")
			String cep) {
		
		Optional<EnderecoModel> busca = cepService.busca(cep);
		
		if(busca.isPresent()) {
			return ResponseEntity.ok(busca.get());
		}
		
		String msg = String.format("CEP %s não encontrado" , cep);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DetailDTO(msg));
	}

}
