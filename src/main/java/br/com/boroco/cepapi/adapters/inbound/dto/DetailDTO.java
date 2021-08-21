package br.com.boroco.cepapi.adapters.inbound.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DetailDTO {

	@ApiModelProperty(value = "Mensagem do erro", example = "CEP inválido")
	private String message;
	
	@ApiModelProperty(value = "Detalhes do erro", example = "O CEP deve conter 8 caracteres numéricos")
	private String detail;
	
	public DetailDTO(String message) {
		this.message = message;
	}

	public DetailDTO(String message, String detail) {
		this.detail = detail;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getDetail() {
		return detail;
	}
	
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
