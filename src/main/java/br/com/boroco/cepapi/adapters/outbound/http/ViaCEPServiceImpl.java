package br.com.boroco.cepapi.adapters.outbound.http;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.boroco.cepapi.core.entities.EnderecoModel;
import br.com.boroco.cepapi.core.ports.RemoteCEPService;

@Component
public class ViaCEPServiceImpl implements RemoteCEPService {
	
	private static final String VIA_CEP_URL = "https://viacep.com.br/ws/%s/json/";

	@Autowired
	private RestTemplate restClient;
	
	@Override
	public Optional<EnderecoModel> buscaRemota(String cep) {
		String url = String.format(VIA_CEP_URL, cep);
		ResponseEntity<EnderecoModel> responseEntity = restClient.getForEntity(url, EnderecoModel.class);
		
		if(responseEntity.getStatusCodeValue() == 200) {
			return Optional.ofNullable(responseEntity.getBody());
		}
		return Optional.ofNullable(null);
	}

}
