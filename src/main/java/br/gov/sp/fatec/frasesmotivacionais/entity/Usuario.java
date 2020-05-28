package br.gov.sp.fatec.frasesmotivacionais.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.gov.sp.fatec.frasesmotivacionais.controller.View;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "usr_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="usr_id")
	private Long id;
	
	@Column(name = "usr_nome", unique=true, length = 20, nullable = false)
	@JsonView(View.FraseCompleta.class)
	private String nome;
	
	@Column(name = "usr_email", unique=true, length = 100, nullable = false)
	@JsonView(View.FraseCompleta.class)
	private String email;
	
	@Column(name = "usr_senha", length = 100, nullable = false)
	private String senha;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "uau_usuario_autorizacao",
		joinColumns = { @JoinColumn(name = "usr_id") },
		inverseJoinColumns = { @JoinColumn(name = "aut_id") })
	private Set<Autorizacao> autorizacoes;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "autor")
	private Set<Frase> frases;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Autorizacao> getAutorizacoes() {
		return autorizacoes;
	}

	public void setAutorizacoes(Set<Autorizacao> autorizacoes) {
		this.autorizacoes = autorizacoes;
	}

	public Set<Frase> getFrases() {
		return frases;
	}

	public void setFrases(Set<Frase> frases) {
		this.frases = frases;
	}

}
