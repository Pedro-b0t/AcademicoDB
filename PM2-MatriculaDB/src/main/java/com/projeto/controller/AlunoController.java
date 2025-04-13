package com.projeto.controller;

import com.projeto.model.Aluno;
import com.projeto.util.JPAUtil;
import jakarta.persistence.EntityManager;
import repositories.AlunoRepository;

import java.util.List;
import java.util.Scanner;

public class AlunoController {

	public static void cadastrarAluno(Scanner scanner) {
		EntityManager em = JPAUtil.getEntityManager();

		String nome;
		do {
			System.out.print("Nome do aluno (apenas letras): ");
			nome = scanner.nextLine();
		} while (!nome.matches("[a-zA-ZÀ-ÿ ]+"));

		System.out.print("RA do aluno: ");
		String ra = scanner.nextLine();

		String cpf;
		do {
			System.out.print("CPF do aluno (apenas números): ");
			cpf = scanner.nextLine();
		} while (!cpf.matches("\\d{11}"));

		String email;
		do {
			System.out.print("Email do aluno: ");
			email = scanner.nextLine();
		} while (!email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")); // \\w.-caract alfanumericos

		String telefone;
		do {
			System.out.print("Telefone do aluno (apenas números, 11 dígitos): ");
			telefone = scanner.nextLine();
		} while (!telefone.matches("\\d{11}"));

		em.getTransaction().begin();
		em.persist(new Aluno(nome, ra, cpf, email, telefone));
		em.getTransaction().commit();
		em.close();

		System.out.println("Aluno cadastrado com sucesso!");
	}

	public static void listarAlunos() {
		EntityManager em = JPAUtil.getEntityManager();
		List<Aluno> alunos = em.createQuery("FROM Aluno", Aluno.class).getResultList();
		alunos.forEach(a -> System.out
				.println(a.getId() + " - " + a.getNome() + " | RA: " + a.getRa() + " | CPF: " + a.getCpf()));
		em.close();
	}

	public static void editarNome(Scanner scanner) {
		EntityManager em = JPAUtil.getEntityManager();
		listarAlunos();
		System.out.print("Digite o ID do aluno que deseja editar: ");
		Long id = scanner.nextLong();
		scanner.nextLine();

		Aluno aluno = em.find(Aluno.class, id);

		if (aluno != null) {
			System.out.print("Novo nome (apenas letras): ");
			String novoNome = scanner.nextLine();
			if (!novoNome.matches("[a-zA-ZÀ-ÿ ]+")) {
				System.out.println("Nome inválido.");
				em.close();
				return;
			}

			em.getTransaction().begin();
			aluno.setNome(novoNome);
			em.getTransaction().commit();

			System.out.println("Nome atualizado com sucesso!");
		} else {
			System.out.println("Aluno não encontrado.");
		}
		em.close();
	}

	public static void buscarnome(Scanner scanner) {

		System.out.println("Digite parte do nome do aluno");
		String termo = scanner.nextLine();

		if (termo.isEmpty()) {
			System.out.println("Por favor, digite um termo válido.");
			return;
		}

		AlunoRepository repo = new AlunoRepository();
		List<Aluno> alunos = repo.buscarPorNome(termo);

		if (alunos.isEmpty()) {
			System.out.println("Nenhum aluno encontrado.");
		} else {
			System.out.println("---Alunos Encontrados---");
			for (Aluno a : alunos) {
				if (a.getNome().toLowerCase().contains(termo.toLowerCase()))
					System.out.println("ID: " + a.getId() + " | Nome : " + a.getNome());
			}
		}
	}
}
