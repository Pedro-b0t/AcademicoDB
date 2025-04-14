package repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

import com.projeto.model.Aluno;

// Classe responsável pela comunicação com o banco de dados relacionada aos alunos
public class AlunoRepository {

	// Instancia a fábrica de EntityManager, responsável por criar as conexões com o
	// banco de dados
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("alunoPU");

	// Método para buscar alunos pelo nome, com base em um termo fornecido
	public List<Aluno> buscarPorNome(String termo) {
		// Cria uma instância do EntityManager para fazer as consultas no banco de dados
		EntityManager em = emf.createEntityManager();

		// Executa a consulta JPQL (Java Persistence Query Language) para buscar alunos
		// cujo nome contenha o termo fornecido
		// O operador LIKE é usado para buscar padrões no nome
		List<Aluno> resultado = em.createQuery("SELECT a FROM Aluno a WHERE a.nome LIKE :nome", Aluno.class)
				.setParameter("nome", "%" + termo + "%") // O parâmetro %...% permite buscar qualquer nome que contenha
															// o termo
				.getResultList(); // Retorna a lista de alunos encontrados

		// Fecha o EntityManager após a execução da consulta
		em.close();
		return resultado; // Retorna a lista de alunos encontrados
	}
}
