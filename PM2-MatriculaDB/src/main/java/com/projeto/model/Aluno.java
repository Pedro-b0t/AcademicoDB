package com.projeto.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

// Marca a classe como uma entidade JPA (será mapeada para uma tabela do banco de dados)
@Entity
public class Aluno {

	// Define o identificador da entidade (chave primária)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto incremento
	private Long id;

	// Atributos do aluno
	private String nome;
	private String ra;
	private String cpf;
	private String email;
	private String telefone;

	// Relacionamento muitos-para-muitos com cursos
	// mappedBy indica que o lado "dono" da relação está na entidade Curso
	@ManyToMany(mappedBy = "alunos")
	private Set<Curso> cursos = new HashSet<>();

	// Construtor vazio exigido pelo JPA
	public Aluno() {
	}

	// Construtor com todos os campos (exceto ID e cursos, que são gerados
	// automaticamente)
	public Aluno(String nome, String ra, String cpf, String email, String telefone) {
		this.nome = nome;
		this.ra = ra;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
	}

	// Getters e setters

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getRa() {
		return ra;
	}

	public String getCpf() {
		return cpf;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	// Retorna os cursos nos quais o aluno está matriculado
	public Set<Curso> getCursos() {
		return cursos;
	}
}
