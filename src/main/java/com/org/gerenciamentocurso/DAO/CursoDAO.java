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

public class CursoDAO {
    //private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("sistemaPU");

    // Create
    public void salvar(Curso curso){
        //Cria uma instância do EntityManager
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(curso);
        em.getTransaction().commit();
        em.close();
    }

    //Update
    public void  editar (Curso curso){
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.merge(curso);
        em.getTransaction().commit();
        em.close();
    }

    //Delete
    public void excluir (Curso curso){
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        curso = em.merge(curso);
        em.remove(curso);
        em.getTransaction().commit();
        em.close();
    }

    // Método para buscar curso por ID
    public Curso buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        Curso curso = em.find(Curso.class, id);
        em.close();
        return curso;
    }

    //Metodo para listar todos os cursos dos bancos de dados
    public List<Curso> findAll() {
        EntityManager em = JPAUtil.getEntityManager(); //Cria uma instância do EntityManeger
        TypedQuery<Curso> query = em.createQuery("SELECT c FROM Curso c", Curso.class);
        List<Curso> cursos = query.getResultList();
        em.close();
        return cursos;

    }
}
