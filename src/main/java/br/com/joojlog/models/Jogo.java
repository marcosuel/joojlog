package br.com.joojlog.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "jogo")
public class Jogo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;
	private int nt_comunidade;
	private int nt_critica;
	
	public Jogo() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getNt_comunidade() {
		return nt_comunidade;
	}

	public void setNt_comunidade(int nt_comunidade) {
		this.nt_comunidade = nt_comunidade;
	}

	public int getNt_critica() {
		return nt_critica;
	}

	public void setNt_critica(int nt_critica) {
		this.nt_critica = nt_critica;
	}


}
