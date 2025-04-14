package com.projeto.controller;

import com.projeto.model.Aluno;
import com.projeto.model.Curso;
import com.projeto.model.Matricula;
import com.projeto.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Scanner;

public class MatriculaController {

	// Método para matricular um aluno em um curso
	public static void matricular(Scanner scanner) {
		EntityManager em = JPAUtil.getEntityManager();

		// Exibe alunos e pede o ID do aluno
		AlunoController.listarAlunos();
		System.out.print("Digite o ID do aluno: ");
		Long alunoId = scanner.nextLong();
		scanner.nextLine(); // Limpa o buffer do Scanner

		// Exibe cursos e pede o ID do curso
		CursoController.listarCursos();
		System.out.print("Digite o ID do curso: ");
		Long cursoId = scanner.nextLong();
		scanner.nextLine(); // Limpa o buffer

		// Busca as entidades aluno e curso
		Aluno aluno = em.find(Aluno.class, alunoId);
		Curso curso = em.find(Curso.class, cursoId);

		// Valida se foram encontrados
		if (aluno == null || curso == null) {
			System.out.println("Aluno ou curso não encontrado.");
			em.close();
			return;
		}

		// Verifica se o aluno já está matriculado no curso
		TypedQuery<Matricula> query = em.createQuery(
			"SELECT m FROM Matricula m WHERE m.aluno = :aluno AND m.curso = :curso", Matricula.class
		);
		query.setParameter("aluno", aluno);
		query.setParameter("curso", curso);

		// Se já existir matrícula, não permite duplicidade
		if (!query.getResultList().isEmpty()) {
			System.out.println("Este aluno já está matriculado neste curso.");
			em.close();
			return;
		}

		// Persiste a nova matrícula
		em.getTransaction().begin();
		Matricula matricula = new Matricula(aluno, curso);
		em.persist(matricula);
		em.getTransaction().commit();
		em.close();

		System.out.println("Aluno matriculado com sucesso!");
	}

	// Lista todas as matrículas existentes
	public static void listar() {
		EntityManager em = JPAUtil.getEntityManager();
		List<Matricula> matriculas = em.createQuery("FROM Matricula", Matricula.class).getResultList();

		System.out.println("\n--- MATRÍCULAS ---");
		for (Matricula m : matriculas) {
			System.out.println("ID: " + m.getId()
				+ " | Aluno: " + m.getAluno().getNome()
				+ " | Curso: " + m.getCurso().getNome());
		}
		em.close();
	}

	// Remove uma matrícula a partir do ID informado
	public static void excluir(Scanner scanner) {
		EntityManager em = JPAUtil.getEntityManager();
		listar(); // Mostra as matrículas antes de excluir
		System.out.print("Digite o ID da matrícula que deseja excluir: ");
		Long id = scanner.nextLong();
		scanner.nextLine(); // Limpa buffer

		Matricula matricula = em.find(Matricula.class, id);

		if (matricula != null) {
			em.getTransaction().begin();
			em.remove(matricula);
			em.getTransaction().commit();
			System.out.println("Matrícula excluída com sucesso!");
		} else {
			System.out.println("Matrícula não encontrada.");
		}
		em.close();
	}

	// Busca todas as matrículas de um aluno através do CPF
	public static void buscarPorCpf(Scanner scanner) {
		EntityManager em = JPAUtil.getEntityManager();
		System.out.print("Digite o CPF do aluno: ");
		String cpf = scanner.nextLine();

		// Busca o aluno pelo CPF
		TypedQuery<Aluno> query = em.createQuery("FROM Aluno a WHERE a.cpf = :cpf", Aluno.class);
		query.setParameter("cpf", cpf);
		List<Aluno> resultado = query.getResultList();

		if (resultado.isEmpty()) {
			System.out.println("Aluno com CPF informado não encontrado.");
			em.close();
			return;
		}

		Aluno aluno = resultado.get(0);
		System.out.println("Aluno encontrado: " + aluno.getNome());

		// Busca as matrículas do aluno
		List<Matricula> matriculas = em.createQuery(
			"FROM Matricula m WHERE m.aluno = :aluno", Matricula.class)
			.setParameter("aluno", aluno)
			.getResultList();

		// Exibe os cursos nos quais o aluno está matriculado
		if (matriculas.isEmpty()) {
			System.out.println("Este aluno não possui matrículas.");
		} else {
			System.out.println("Matrículas do aluno:");
			for (Matricula m : matriculas) {
				System.out.println("- Curso: " + m.getCurso().getNome());
			}
		}
		em.close();
	}

	// Remove todos os dados do banco (matrículas, cursos e alunos)
	public static void limparTudo() {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();

		// Remove na ordem correta para evitar erro de integridade
		em.createQuery("DELETE FROM Matricula").executeUpdate();
		em.createQuery("DELETE FROM Curso").executeUpdate();
		em.createQuery("DELETE FROM Aluno").executeUpdate();

		em.getTransaction().commit();
		em.close();
		System.out.println("Todos os dados foram excluídos com sucesso.");
	}
}
