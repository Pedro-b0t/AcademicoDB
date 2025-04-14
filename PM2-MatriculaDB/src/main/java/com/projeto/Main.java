package com.projeto;

// Importações das classes dos controladores e utilitários
import com.projeto.controller.AlunoController;
import com.projeto.controller.CursoController;
import com.projeto.controller.MatriculaController;
import com.projeto.model.Aluno;
import com.projeto.util.JPAUtil;
import repositories.AlunoRepository;

import java.util.List;
import java.util.Scanner;

public class Main {
	private static final Scanner scanner = new Scanner(System.in); // Scanner para entrada do usuário

	public static void main(String[] args) {
		int opcao;
		// Estrutura de repetição do menu principal
		do {
			// Exibição do menu de opções
			System.out.println("\n--- MENU ---");
			System.out.println("1. Cadastrar aluno");
			System.out.println("2. Listar alunos");
			System.out.println("3. Cadastrar curso");
			System.out.println("4. Listar cursos");
			System.out.println("5. Matricular aluno em curso");
			System.out.println("6. Listar matrículas");
			System.out.println("7. Editar nome de aluno");
			System.out.println("8. Excluir matrícula");
			System.out.println("9. Buscar matrícula por CPF");
			System.out.println("10. Limpar todos os dados");
			System.out.println("11. Pesquisar aluno pelo nome");
			System.out.println("0. Sair");
			System.out.print("Escolha: ");
			
			opcao = scanner.nextInt(); // Lê a opção digitada
			scanner.nextLine(); // Limpa o buffer de entrada (evita erros de leitura)

			// Estrutura de decisão com switch para executar a ação escolhida
			switch (opcao) {
				case 1 -> AlunoController.cadastrarAluno(scanner); // Chama o método de cadastro de aluno
				case 2 -> AlunoController.listarAlunos(); // Lista todos os alunos cadastrados
				case 3 -> CursoController.cadastrarCurso(scanner); // Cadastra um novo curso
				case 4 -> CursoController.listarCursos(); // Lista todos os cursos
				case 5 -> MatriculaController.matricular(scanner); // Matricula um aluno em um curso
				case 6 -> MatriculaController.listar(); // Lista todas as matrículas
				case 7 -> AlunoController.editarNome(scanner); // Edita o nome de um aluno
				case 8 -> MatriculaController.excluir(scanner); // Exclui uma matrícula
				case 9 -> MatriculaController.buscarPorCpf(scanner); // Busca matrícula pelo CPF do aluno
				case 10 -> MatriculaController.limparTudo(); // Apaga todos os dados do sistema
				case 11 -> AlunoController.buscarnome(scanner); // Busca aluno pelo nome
				case 0 -> System.out.println("Encerrando..."); // Encerra o programa
				default -> System.out.println("Opção inválida."); // Trata entradas inválidas
			}

		} while (opcao != 0); // O loop continua até o usuário escolher sair

		JPAUtil.close(); // Encerra a conexão com o JPA/Hibernate
	}

	// Método auxiliar (provavelmente usado para testes)
	public static void main1(String[] args) {
		AlunoRepository repo = new AlunoRepository(); // Cria um repositório para buscar alunos

		List<Aluno> alunos = repo.buscarPorNome("ana"); // Busca alunos cujo nome contém "ana"

		// Imprime os resultados encontrados
		for (Aluno a : alunos) {
			System.out.println("ID: " + a.getId() + " | Nome: " + a.getNome());
		}
	}
}
