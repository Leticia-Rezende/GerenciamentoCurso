package com.org.gerenciamentocurso.DAO;

import com.org.gerenciamentocurso.Model.Professor;
import com.org.gerenciamentocurso.Utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ProfessorDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("sistemaPU");

    // Create
    public void salvar(Professor professor){
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(professor);
        em.getTransaction().commit();
        em.close();
    }

    //Update
    public void  editar (Professor professor){
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.merge(professor);
        em.getTransaction().commit();
        em.close();
    }

    //Delete
    public void excluir (Professor professor){
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        professor = em.merge(professor);
        em.remove(professor);
        em.getTransaction().commit();
        em.close();
    }

    // Método para buscar curso por ID
    public Professor buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        Professor professor = em.find(Professor.class, id);
        em.close();
        return professor;
    }

    //Metodo para listar todos os cursos dos bancos de dados

    public List<Professor> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<Professor> query = em.createQuery("SELECT p FROM Professor p", Professor.class);
        List<Professor> professores = query.getResultList();
        em.close();
        return professores;
    }

}
