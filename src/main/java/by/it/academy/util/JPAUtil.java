package by.it.academy.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil implements JPA{
    JPA jpa;

    static final EntityManager ENTITY_MANAGER =  buildEntityManager();

    private static EntityManager buildEntityManager(){
        EntityManagerFactory employeesTesting =
                Persistence.createEntityManagerFactory("HelloWorld");
        EntityManager entityManager = employeesTesting.createEntityManager();
        return entityManager;
    }

    @Override
    public EntityManager getEntityManager() {
        return ENTITY_MANAGER;
    }
}
