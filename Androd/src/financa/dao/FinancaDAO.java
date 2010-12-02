/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package financa.dao;

import androd.dao.AndrodDAO;
import androd.dao.JPADAOFactory;
import financa.to.Financa;
import java.lang.reflect.Field;
import java.util.List;
import javax.persistence.EntityManager;
import org.apache.log4j.Logger;

/**
 *
 * @author iskigow
 */
public class FinancaDAO implements AndrodDAO<Financa> {

    private String METODO = "QUE METODO?!";
    private final static Logger log = Logger.getLogger(FinancaDAO.class);
    private EntityManager entityManager = null;

    private synchronized void logEntrada(String METODO) {
        this.METODO = METODO;
        log.debug("# ENTROU # Classe: " + this.getClass().getCanonicalName() + " - Método: " + this.METODO);
    }

    private synchronized void logSaida() {
        log.debug("# SAIU #Classe: " + this.getClass().getCanonicalName() + " - Método: " + this.METODO);
        this.METODO = "QUE METODO?!";
    }

    public Financa recuperaObjeto(Financa objetoParametro, Field ... forFields) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Financa> recuperaObjetos() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Financa recuperaObjeto(Financa objetoParametro) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void inclui(Financa financa) throws Exception {
         logEntrada("inclui");

        try {

            entityManager = JPADAOFactory.createEntityManager();

            entityManager.getTransaction().begin();

            if (financa == null) {
                throw new NullPointerException("Finança inválida.");
            }

            entityManager.persist(financa);
            entityManager.getTransaction().commit();

        } catch (Exception e) {

            entityManager.getTransaction().rollback();
            log.error("Ocorreu um erro", e);
            throw e;
        } finally {
            entityManager.close();
        }
        logSaida();
    }

    public void altera(Financa financa) throws Exception {
        logEntrada("altera");

        try {

            entityManager = JPADAOFactory.createEntityManager();

            entityManager.getTransaction().begin();

            if (financa == null) {
                throw new NullPointerException("Finança inválida.");
            }

            entityManager.merge(financa);
            entityManager.getTransaction().commit();

        } catch (Exception e) {

            entityManager.getTransaction().rollback();
            log.error("Ocorreu um erro", e);
            throw e;
        } finally {
            entityManager.close();
        }
        logSaida();
    }

    public void exclui(Financa entidadeInstancia) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
