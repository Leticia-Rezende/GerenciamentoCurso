package com.org.gerenciamentocurso.DAO;

import com.org.gerenciamentocurso.Model.Professor;
import com.org.gerenciamentocurso.Model.Turma;
import com.org.gerenciamentocurso.Utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;


public class TurmaDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("sistemaPU");
    private static EntityManagerFactory factory;

    // Create
    public void salvar(Turma turma){
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(turma);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    //Update
    public void  editar (Turma turma){
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            turma = em.merge(turma);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    //Delete
    public void excluir (Long id){
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            Turma turma = em.find(Turma.class, id);
            if (turma != null) {
                em.remove(turma);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // Método para buscar curso por ID
    public Turma buscarPorId(Long id) {
        try (EntityManager em =  JPAUtil.getEntityManager()) { //emf.createEntityManager())
            return em.find(Turma.class, id); // Retorna o livro ou null se não encontrado
        }
    }

    //Metodo para listar todos os cursos dos bancos de dados
    public List<Turma> listar () {
        EntityManager em = emf.createEntityManager(); //Cria uma instância do EntityManeger
        try {
            //Executa uma consulta JPQL para buscar todos os usuários
            return  em.createQuery("SELECT u FROM Turma u ", Turma.class).getResultList();
        } finally {
            em.close(); // Fecha o EntityManeher para liberar recursos
        }
    }
    public List<Turma> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<Turma> query = em.createQuery("SELECT p FROM Turma p", Turma.class);
        List<Turma> turmas = query.getResultList();
        em.close();
        return turmas;
    }

}
