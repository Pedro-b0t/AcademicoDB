package com.projeto.controller;

import com.projeto.model.Aluno;
import com.projeto.util.JPAUtil;
import jakarta.persistence.EntityManager;
import repositories.AlunoRepository;

import java.util.List;
import java.util.Scanner;

public class AlunoController {

	// Método para cadastrar um novo aluno
	public static void cadastrarAluno(Scanner scanner) {
		EntityManager em = JPAUtil.getEntityManager(); // Obtém o EntityManager para interagir com o banco de dados

		String nome;
		// Validação para garantir que o nome contém apenas letras e espaços
		do {
			System.out.print("Nome do aluno (apenas letras): ");
			nome = scanner.nextLine();
		} while (!nome.matches("[a-zA-ZÀ-ÿ ]+"));

		System.out.print("RA do aluno: ");
		String ra = scanner.nextLine();

		String cpf;
		// Validação do CPF: exatamente 11 dígitos
		do {
			System.out.print("CPF do aluno (apenas números): ");
			cpf = scanner.nextLine();
		} while (!cpf.matches("\\d{11}"));

		String email;
		// Validação de e-mail usando regex
		do {
			System.out.print("Email do aluno: ");
			email = scanner.nextLine();
		} while (!email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")); // Regex para e-mails válidos

		String telefone;
		// Validação de telefone: exatamente 11 dígitos (formato brasileiro comum com DDD)
		do {
			System.out.print("Telefone do aluno (apenas números, 11 dígitos): ");
			telefone = scanner.nextLine();
		} while (!telefone.matches("\\d{11}"));

		// Início da transação e persistência do novo aluno
		em.getTransaction().begin();
		em.persist(new Aluno(nome, ra, cpf, email, telefone));
		em.getTransaction().commit();
		em.close();

		System.out.println("Aluno cadastrado com sucesso!");
	}

	// Método para listar todos os alunos cadastrados
	public static void listarAlunos() {
		EntityManager em = JPAUtil.getEntityManager();
		
		// Consulta JPQL para buscar todos os alunos
		List<Aluno> alunos = em.createQuery("FROM Aluno", Aluno.class).getResultList();
		
		// Exibe os alunos encontrados
		alunos.forEach(a -> System.out
			.println(a.getId() + " - " + a.getNome() + " | RA: " + a.getRa() + " | CPF: " + a.getCpf()));
		
		em.close();
	}

	// Método para editar o nome de um aluno
	public static void editarNome(Scanner scanner) {
		EntityManager em = JPAUtil.getEntityManager();

		// Exibe lista de alunos para facilitar a escolha
		listarAlunos();
		System.out.print("Digite o ID do aluno que deseja editar: ");
		Long id = scanner.nextLong();
		scanner.nextLine(); // Limpa o buffer

		// Busca o aluno pelo ID
		Aluno aluno = em.find(Aluno.class, id);

		if (aluno != null) {
			System.out.print("Novo nome (apenas letras): ");
			String novoNome = scanner.nextLine();

			// Valida o novo nome
			if (!novoNome.matches("[a-zA-ZÀ-ÿ ]+")) {
				System.out.println("Nome inválido.");
				em.close();
				return;
			}

			// Atualiza o nome do aluno
			em.getTransaction().begin();
			aluno.setNome(novoNome);
			em.getTransaction().commit();

			System.out.println("Nome atualizado com sucesso!");
		} else {
			System.out.println("Aluno não encontrado.");
		}
		em.close();
	}

	// Método para buscar alunos pelo nome (ou parte dele)
	public static void buscarnome(Scanner scanner) {
		System.out.println("Digite parte do nome do aluno");
		String termo = scanner.nextLine();

		if (termo.isEmpty()) {
			System.out.println("Por favor, digite um termo válido.");
			return;
		}

		// Utiliza o repositório para buscar alunos cujo nome contém o termo
		AlunoRepository repo = new AlunoRepository();
		List<Aluno> alunos = repo.buscarPorNome(termo);

		if (alunos.isEmpty()) {
			System.out.println("Nenhum aluno encontrado.");
		} else {
			System.out.println("---Alunos Encontrados---");
			for (Aluno a : alunos) {
				// Garante que o nome realmente contém o termo (em letras minúsculas)
				if (a.getNome().toLowerCase().contains(termo.toLowerCase()))
					System.out.println("ID: " + a.getId() + " | Nome : " + a.getNome());
			}
		}
	}
}
