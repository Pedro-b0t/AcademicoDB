package com.projeto.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

// Marca esta classe como uma entidade JPA, representando uma tabela no banco de dados
@Entity
public class Curso {

	// Chave primária gerada automaticamente com auto incremento
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Nome do curso
	private String nome;

	// Relacionamento muitos-para-muitos com a entidade Aluno
	// O lado "dono" da relação é este (não tem mappedBy aqui, ao contrário da
	// entidade Aluno)
	@ManyToMany
	private Set<Aluno> alunos = new HashSet<>();

	// Construtor padrão (necessário para o JPA)
	public Curso() {
	}

	// Construtor com nome (usado ao cadastrar um curso)
	public Curso(String nome) {
		this.nome = nome;
	}

	// Getters e Setters
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Set<Aluno> getAlunos() {
		return alunos;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
