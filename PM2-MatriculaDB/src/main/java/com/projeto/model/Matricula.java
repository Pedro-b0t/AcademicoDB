package com.projeto.model;

import jakarta.persistence.*;

// Define que esta classe é uma entidade JPA e será mapeada para a tabela "matricula"
@Entity
@Table(name = "matricula")
public class Matricula {

	// Identificador único da matrícula (chave primária), gerado automaticamente
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Relacionamento muitos-para-um com a entidade Aluno
	// Uma matrícula está associada a um aluno
	@ManyToOne
	private Aluno aluno;

	// Relacionamento muitos-para-um com a entidade Curso
	// Uma matrícula está associada a um curso
	@ManyToOne
	private Curso curso;

	// Construtor padrão exigido pelo JPA
	public Matricula() {
	}

	// Construtor utilizado para criar uma nova matrícula entre um aluno e um curso
	public Matricula(Aluno aluno, Curso curso) {
		this.aluno = aluno;
		this.curso = curso;
	}

	// Getters e Setters
	public Long getId() {
		return id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
}
