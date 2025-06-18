package com.org.gerenciamentocurso.DAO;

import com.org.gerenciamentocurso.Model.Curso;
import com.org.gerenciamentocurso.Model.Disciplina;
import com.org.gerenciamentocurso.Model.Turma;
import com.org.gerenciamentocurso.Utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("sistemaPU");

    // Método para salvar um novo curso no banco de dados
    public void salvar(Disciplina disciplina){
        //Cria uma instância do EntityManager
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(disciplina);
            em.getTransaction().commit();
        }
    }

    //Metodo para editar uma materia existente no banco de dados
    public void  editar (Disciplina aluno){
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin(); // Inicia uma transação
            em.merge(aluno); // Atualiza o objeto no banco de dados
            em.getTransaction().commit(); // Confirma a transação
        }
    }

    //Metodo para excluir um curso do banco de dados
    public void excluir (Long id){
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin(); //Inicia uma transação
            Disciplina disciplina = em.find(Disciplina.class, id); // Busca o usuário pelo ID
            if (disciplina!= null){
                em.remove(disciplina); //Remove o curso do banco de dados
            }
            em.getTransaction().commit(); //Confirma a transação
        }
    }

    // Método para buscar curso por ID
    public Disciplina buscarPorId(Long id) {
        try (EntityManager em =  JPAUtil.getEntityManager()) { //emf.createEntityManager())
            return em.find(Disciplina.class, id); // Retorna o livro ou null se não encontrado
        }
    }

    //Metodo para listar todos os cursos dos bancos de dados
    public List<Disciplina> findAll() {
        EntityManager em = emf.createEntityManager(); //Cria uma instância do EntityManeger
        try {
            //Executa uma consulta JPQL para buscar todos os usuários
            return  em.createQuery("SELECT u FROM Disciplina u ", Disciplina.class).getResultList();
        } finally {
            em.close(); // Fecha o EntityManeher para liberar recursos
        }
    }

    // Metódo para fechar a fabrica de EntityManeger
    public void fechar (){
        emf.close(); //Fecha o EntityManegerGactory
    }
}
