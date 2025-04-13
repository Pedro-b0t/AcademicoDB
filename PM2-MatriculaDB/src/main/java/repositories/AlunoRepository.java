package repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

import com.projeto.model.Aluno;

public class AlunoRepository {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("alunoPU");

	public List<Aluno> buscarPorNome(String termo) {
		EntityManager em = emf.createEntityManager();

		List<Aluno> resultado = em.createQuery(

				"SELECT a FROM Aluno a WHERE a.nome LIKE :nome", Aluno.class).setParameter("nome", "%" + termo + "%")
				.getResultList();

		em.close();
		return resultado;

	}
}
