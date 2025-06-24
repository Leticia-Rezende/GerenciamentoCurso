package com.org.gerenciamentocurso.DAO;

import com.org.gerenciamentocurso.Model.Curso;
import com.org.gerenciamentocurso.Model.Disciplina;
import com.org.gerenciamentocurso.Model.Turma;
import com.org.gerenciamentocurso.Utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("sistemaPU");

    // Create
    public void salvar(Disciplina disciplina) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(disciplina);
        em.getTransaction().commit();
        em.close();
    }

    //Update
    public void editar(Disciplina disciplina) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();// Inicia uma transação
        em.merge(disciplina);// Atualiza o objeto no banco de dados
        em.getTransaction().commit(); // Confirma a transação
        em.close();
    }

    //Delete
    public void excluir(Disciplina disciplina) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        disciplina = em.merge(disciplina);
        em.remove(disciplina);
        em.getTransaction().commit();
        em.close();
    }

    // Método para buscar curso por ID
    public Disciplina buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        Disciplina disciplina = em.find(Disciplina.class, id); // Retorna o livro ou null se não encontrado
        em.close();
        return disciplina;
    }

    //Metodo para listar todos os cursos dos bancos de dados
    public List<Disciplina> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<Disciplina> query = em.createQuery("SELECT d FROM Disciplina d", Disciplina.class);
        List<Disciplina> disciplinas = query.getResultList();
        em.close();
        return disciplinas;
    }

}