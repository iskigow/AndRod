package androd.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 
 * 
 */
public class JPADAOFactory {

    private static EntityManagerFactory emf;

    public static void createEntityManagerFactory() {
        if(emf == null){
            emf = Persistence.createEntityManagerFactory("AndrodPU");
        }
    }

    public static EntityManager createEntityManager() {
        return emf.createEntityManager();
    }
}