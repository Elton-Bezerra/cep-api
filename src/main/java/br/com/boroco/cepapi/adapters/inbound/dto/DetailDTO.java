package br.com.boroco.cepapi.adapters.inbound.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DetailDTO {

	private String message;
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
