package br.com.boroco.cepapi.core.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "TB_ENDERECO")
@ApiModel
public class EnderecoModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

    @ApiModelProperty(name = "cep", position = 1, example = "12345678")
	private String cep;
    
    @ApiModelProperty(name = "logradouro", position = 2, example = "Rua Apucarana")
	private String logradouro;
    
    @ApiModelProperty(name = "bairro", position = 3, example = "Vila Gomes Cardim")
	private String bairro;
    
    @ApiModelProperty(name = "localidade", position = 4, example = "São Paulo")    
	private String localidade;
    
    @ApiModelProperty(name = "uf guid", position = 5, example = "SP")
	private String uf;

	public EnderecoModel() {
	}

	public EnderecoModel(String cep, String logradouro, String bairro, String localidade, String uf) {
		this.cep = cep;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

}
