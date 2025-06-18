package com.org.gerenciamentocurso.DAO;

import com.org.gerenciamentocurso.Model.Professor;
import com.org.gerenciamentocurso.Model.Turma;
import com.org.gerenciamentocurso.Utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class TurmaDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("sistemaPU");

    // Método para salvar um novo curso no banco de dados
    public void salvar(Turma turma){
        //Cria uma instância do EntityManager
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(turma);
            em.getTransaction().commit();
        }
    }

    //Metodo para editar uma materia existente no banco de dados
    public void  editar (Turma sala){
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin(); // Inicia uma transação
            em.merge(sala); // Atualiza o objeto no banco de dados
            em.getTransaction().commit(); // Confirma a transação
        }
    }

    //Metodo para excluir um curso do banco de dados
    public void excluir (Long id){
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin(); //Inicia uma transação
            Turma turma = em.find(Turma.class, id); // Busca o usuário pelo ID
            if (turma!= null){
                em.remove(turma); //Remove o curso do banco de dados
            }
            em.getTransaction().commit(); //Confirma a transação
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

    // Metódo para fechar a fabrica de EntityManeger
    public void fechar (){
        emf.close(); //Fecha o EntityManegerGactory
    }
}
