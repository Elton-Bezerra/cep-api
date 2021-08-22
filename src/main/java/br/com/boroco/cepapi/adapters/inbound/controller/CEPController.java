package br.com.boroco.cepapi.adapters.inbound.controller;

import java.util.Optional;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.boroco.cepapi.adapters.inbound.dto.DetailDTO;
import br.com.boroco.cepapi.core.entities.EnderecoModel;
import br.com.boroco.cepapi.core.ports.CepService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Validated
@Api(produces = "application/json")
public class CEPController {
	
	@Autowired
	private CepService cepService;
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	private Logger logger = LoggerFactory.getLogger(CEPController.class);
	
    @ExceptionHandler({ ConstraintViolationException.class, JsonProcessingException.class})
    public ResponseEntity<?> handleException(ConstraintViolationException ex) {
    	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DetailDTO("CEP inválido", ex.getMessage()));
    }
	
    
    
    @ApiOperation(
    		value="Busca um CEP localmente, caso não encontre, tenta buscar no provedor de CEPs para responder nas próximas chamadas",
    		response=EnderecoModel.class,
    		produces = "application/json")
	@GetMapping(path="/busca/{cep}", produces = "application/json")
	@Cacheable(value="ceps", unless = "#result.getStatusCodeValue()!=200")
    @ApiResponses(value = {
    	    @ApiResponse(code = 200, message = "Retorna o endereço para o CEP informado"),
    	    @ApiResponse(code = 400, message = "CEP informado é inválido", response = DetailDTO.class),
    	    @ApiResponse(code = 404, message = "CEP não encontrado"),
    	})	
    public ResponseEntity<?> busca(
			@PathVariable(name = "cep") 
			@Size(min = 8, max = 8, message = "O CEP deve ter 8 caracteres")
			@Pattern(regexp = "(^[0-9]{8})", message = "Apenas caracteres númericos são permitidos")
			String cep) throws JsonProcessingException {
		

		Optional<EnderecoModel> busca = cepService.busca(cep);
		
		if(busca.isPresent()) {
			logger.info(mapper.writeValueAsString(busca.get()));
			return ResponseEntity.ok(busca.get());
		}
		
		String msg = String.format("CEP %s não encontrado" , cep);

		logger.info(msg);

		cepService.buscarCepRemotamente(cep);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DetailDTO(msg));
	}
	
	

}
