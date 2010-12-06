/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package financa.dao;

import androd.dao.AndrodDAO;
import androd.dao.JPADAOFactory;
import financa.to.Financa;
import financa.to.TipoFinanca;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.apache.log4j.Logger;
import usuario.to.Usuario;

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
        log.info("# ENTROU # Classe: " + this.getClass().getCanonicalName() + " - Método: " + this.METODO);
    }

    private synchronized void logSaida() {
        log.info("# SAIU #Classe: " + this.getClass().getCanonicalName() + " - Método: " + this.METODO);
        this.METODO = "QUE METODO?!";
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
        logEntrada("exclui");
        try {
            entityManager = JPADAOFactory.createEntityManager();
            entityManager.getTransaction().begin();

            if (entidadeInstancia == null) {
                throw new NullPointerException("Finança Invalida.");
            }

            entidadeInstancia = entityManager.merge(entidadeInstancia);
            entityManager.remove(entidadeInstancia);
            entityManager.getTransaction().commit();

        }catch (Exception e) {
            log.error("Ocorreu um erro na exclusão da Finança. Método: exclui", e);

            entityManager.getTransaction().rollback();

            throw e;
        }finally {
            entityManager.close();
        }
        logSaida();
    }

    public List<Financa> recuperaObjetos(Usuario usuarioLogado) throws Exception {
        logEntrada("recuperaObjetos");
        try {
            entityManager = JPADAOFactory.createEntityManager();
            entityManager.getTransaction().begin();

            TypedQuery<Financa> query = entityManager.createNamedQuery("Financa.findAll",
                    Financa.class).setParameter("usuario", usuarioLogado);
            List<Financa> cfs = query.getResultList();
            return cfs != null && !cfs.isEmpty() ? cfs : null;
        } catch (Exception e) {
            log.error("Ocorreu um erro", e);
            throw e;
        } finally {
            entityManager.close();
            logSaida();
        }
    }

    public List<Financa> recuperaObjetos(TipoFinanca tipoFinanca, Usuario usuarioLogado) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Financa recuperaObjetoPorId(Financa financa) throws Exception {
        logEntrada("recuperaObjetoPorId");
        try {
            entityManager = JPADAOFactory.createEntityManager();

            entityManager.getTransaction().begin();

            TypedQuery<Financa> query = entityManager.createNamedQuery("Financa.findById",
                    Financa.class).setParameter("id", financa.getId()).setParameter("usuario", financa.getUsuario());
            List<Financa> cfs = query.getResultList();
            return cfs != null && !cfs.isEmpty() ? cfs.get(0) : null;
        } catch (Exception e) {
            log.error("Ocorreu um erro", e);
            throw e;
        } finally {
            entityManager.close();
            logSaida();
        }
    }
}
