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
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("sistemaPU");

    // Método para salvar um novo curso no banco de dados
    public void salvar(Curso curso){
        //Cria uma instância do EntityManager
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(curso);
            em.getTransaction().commit();
        }
    }

    //Metodo para editar uma materia existente no banco de dados
    public void  editar (Curso materia){
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin(); // Inicia uma transação
            em.merge(materia); // Atualiza o objeto no banco de dados
            em.getTransaction().commit(); // Confirma a transação
        }
    }

    //Metodo para excluir um curso do banco de dados
    public void excluir (Long id){
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin(); //Inicia uma transação
            Curso curso = em.find(Curso.class, id); // Busca o usuário pelo ID
            if (curso!= null){
                em.remove(curso); //Remove o curso do banco de dados
            }
            em.getTransaction().commit(); //Confirma a transação
        }
    }

    // Método para buscar curso por ID
    public Curso buscarPorId(Long id) {
        try (EntityManager em =  JPAUtil.getEntityManager()) { //emf.createEntityManager())
            return em.find(Curso.class, id); // Retorna o livro ou null se não encontrado
        }
    }

    //Metodo para listar todos os cursos dos bancos de dados
    public List<Curso> findAll() {
        EntityManager em = emf.createEntityManager(); //Cria uma instância do EntityManeger
        try {
            //Executa uma consulta JPQL para buscar todos os usuários
            return  em.createQuery("SELECT u FROM Curso u ", Curso.class).getResultList();
        } finally {
            em.close(); // Fecha o EntityManeher para liberar recursos
        }
    }

    // Metódo para fechar a fabrica de EntityManeger
    public void fechar (){
        emf.close(); //Fecha o EntityManegerGactory
    }

}
