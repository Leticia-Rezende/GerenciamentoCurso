package com.org.gerenciamentocurso.Utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
        private static final EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("sistemaPU"); // Nome do persistence-unit

        public static EntityManager getEntityManager() {
            return emf.createEntityManager();
        }

        public static void close() {
            emf.close();
        }
}
