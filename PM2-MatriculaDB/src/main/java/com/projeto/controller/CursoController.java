package com.projeto.controller;

import com.projeto.model.Curso;
import com.projeto.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Scanner;

public class CursoController {

	// Método para cadastrar um novo curso
	public static void cadastrarCurso(Scanner scanner) {
		EntityManager em = JPAUtil.getEntityManager(); // Cria o gerenciador de entidades

		String nome;
		// Loop para garantir que o nome inserido contenha apenas letras (com acentuação) e espaços
		do {
			System.out.print("Nome do curso (apenas letras): ");
			nome = scanner.nextLine();
		} while (!nome.matches("[a-zA-ZÀ-ÿ ]+")); // Regex para validar apenas letras (inclusive acentuadas)

		// Início da transação para salvar o curso
		em.getTransaction().begin();
		em.persist(new Curso(nome)); // Persiste o novo objeto Curso
		em.getTransaction().commit(); // Finaliza a transação
		em.close(); // Fecha o EntityManager

		System.out.println("Curso cadastrado com sucesso!");
	}

	// Método para listar todos os cursos cadastrados
	public static void listarCursos() {
		EntityManager em = JPAUtil.getEntityManager();

		// Consulta todos os cursos cadastrados usando JPQL
		List<Curso> cursos = em.createQuery("FROM Curso", Curso.class).getResultList();

		// Exibe os cursos encontrados
		cursos.forEach(c -> System.out.println(c.getId() + " - " + c.getNome()));

		em.close(); // Fecha o EntityManager
	}
}
