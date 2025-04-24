package com.projeto;

import com.projeto.controller.AlunoController;
import com.projeto.controller.CursoController;
import com.projeto.controller.MatriculaController;
import com.projeto.model.Usuario;
import com.projeto.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        if (!fazerLogin()) {
            System.out.println("Encerrando o sistema.");
            return;
        }

        int opcao;
        do {
            limparConsole();
            System.out.println("--- MENU ---");
            System.out.println("1. Cadastrar aluno");
            System.out.println("2. Listar alunos");
            System.out.println("3. Cadastrar curso");
            System.out.println("4. Listar cursos");
            System.out.println("5. Matricular aluno em curso");
            System.out.println("6. Listar matriculas");
            System.out.println("7. Editar nome de aluno");
            System.out.println("8. Excluir matrícula");
            System.out.println("9. Buscar matrícula por CPF");
            System.out.println("10. Limpar todos os dados");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> AlunoController.cadastrarAluno(scanner);
                case 2 -> AlunoController.listarAlunos();
                case 3 -> CursoController.cadastrarCurso(scanner);
                case 4 -> CursoController.listarCursos();
                case 5 -> MatriculaController.matricular(scanner);
                case 6 -> MatriculaController.listar();
                case 7 -> AlunoController.editarNome(scanner);
                case 8 -> MatriculaController.excluir(scanner);
                case 9 -> MatriculaController.buscarPorCpf(scanner);
                case 10 -> MatriculaController.limparTudo();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        JPAUtil.close();
    }

    public static void limparConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Não foi possível limpar a tela.");
        }
    }

    public static void aguardarEnter() {
        System.out.print("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }

    public static boolean fazerLogin() {
        EntityManager em = JPAUtil.getEntityManager();
        System.out.println("=== LOGIN ===");
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        try {
            Usuario usuario = em.createQuery(
                    "FROM Usuario u WHERE u.login = :login AND u.senha = :senha", Usuario.class)
                    .setParameter("login", login)
                    .setParameter("senha", senha)
                    .getSingleResult();

            System.out.println("Login bem-sucedido! Bem-vindo, " + usuario.getLogin());
            aguardarEnter();
            limparConsole();
            return true;
        } catch (Exception e) {
            System.out.println("Usuario ou senha invalidos.");
            return false;
        } finally {
            em.close();
        }
    }
}
