/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package financa.dao;

import androd.dao.AndrodDAO;
import androd.dao.JPADAOFactory;
import financa.to.ClassificacaoFinanca;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.apache.log4j.Logger;
import usuario.to.Usuario;

/**
 *
 * @author iskigow
 */
public class ClassificacaoFinancaDAO implements AndrodDAO<ClassificacaoFinanca> {

    private String METODO = "QUE METODO?!";
    private final static Logger log = Logger.getLogger(ClassificacaoFinancaDAO.class);
    private EntityManager entityManager = null;

    private synchronized void logEntrada(String METODO) {
        this.METODO = METODO;
        log.debug("# ENTROU # Classe: " + this.getClass().getCanonicalName() + " - Método: " + this.METODO);
    }

    private synchronized void logSaida() {
        log.debug("# SAIU #Classe: " + this.getClass().getCanonicalName() + " - Método: " + this.METODO);
        this.METODO = "QUE METODO?!";
    }

    public void inclui(ClassificacaoFinanca classificacaoFinanca) throws Exception {

        logEntrada("inclui");

        try {

            entityManager = JPADAOFactory.createEntityManager();

            entityManager.getTransaction().begin();

            if (classificacaoFinanca == null) {
                throw new NullPointerException("Classificação Finança inválida.");
            }

            if (classificacaoFinanca.getSuperior() != null
                    && (classificacaoFinanca.getSuperior().getId() == null
                    || classificacaoFinanca.getSuperior().getId().isEmpty())) {
                classificacaoFinanca.setSuperior(null);
            } 

            entityManager.persist(classificacaoFinanca);
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

    public List<ClassificacaoFinanca> recuperaObjetos() throws Exception {
        logEntrada("recuperaObjetos");
        throw new UnsupportedOperationException("Not supported yet.");
        //logSaida();
    }

    public ClassificacaoFinanca recuperaObjeto(ClassificacaoFinanca objetoParametro) throws Exception {
        logEntrada("recuperaObjeto");
        throw new UnsupportedOperationException("Not supported yet.");
        //logSaida();
    }

    public void altera(ClassificacaoFinanca classificacaoFinanca) throws Exception {
        logEntrada("altera");

        try {

            entityManager = JPADAOFactory.createEntityManager();

            entityManager.getTransaction().begin();

            if (classificacaoFinanca == null) {
                throw new NullPointerException("Classificação Finança inválida.");
            }

            if (classificacaoFinanca.getSuperior() != null
                    && (classificacaoFinanca.getSuperior().getId() == null
                    || classificacaoFinanca.getSuperior().getId().isEmpty())) {
                classificacaoFinanca.setSuperior(null);
            }

            entityManager.merge(classificacaoFinanca);
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

    public ClassificacaoFinanca recuperaObjetoPorId(ClassificacaoFinanca superior, Usuario usuario) throws Exception {
        logEntrada("recuperaObjetoPorId");
        try {
            entityManager = JPADAOFactory.createEntityManager();

            entityManager.getTransaction().begin();

            TypedQuery<ClassificacaoFinanca> query = entityManager.createNamedQuery("ClassificacaoFinanca.findById",
                    ClassificacaoFinanca.class).setParameter("id", superior.getId()).setParameter("usuario", usuario);
            List<ClassificacaoFinanca> cfs = query.getResultList();
            return cfs != null && !cfs.isEmpty() ? cfs.get(0) : null;
        } catch (Exception e) {
            log.error("Ocorreu um erro", e);
            throw e;
        } finally {
            entityManager.close();
            logSaida();
        }
    }

    public List<ClassificacaoFinanca> recuperaObjetos(Usuario usuario) throws Exception {
        logEntrada("recuperaObjetoPorId");
        try {
            entityManager = JPADAOFactory.createEntityManager();
            entityManager.getTransaction().begin();
            
            TypedQuery<ClassificacaoFinanca> query = entityManager.createNamedQuery("ClassificacaoFinanca.findAll",
                    ClassificacaoFinanca.class).setParameter("usuario", usuario);
            List<ClassificacaoFinanca> cfs = query.getResultList();
            return cfs != null && !cfs.isEmpty() ? cfs : null;
        } catch (Exception e) {
            log.error("Ocorreu um erro", e);
            throw e;
        } finally {
            entityManager.close();
            logSaida();
        }
    }

    public List<ClassificacaoFinanca> recuperaObjetosUltimoNivel(Usuario usuarioLogado) throws Exception {
        logEntrada("recuperaObjetoPorId");
        try {
            entityManager = JPADAOFactory.createEntityManager();

            entityManager.getTransaction().begin();
            TypedQuery<ClassificacaoFinanca> query = entityManager.createNamedQuery("ClassificacaoFinanca.findAllUltimoNivel",
                    ClassificacaoFinanca.class).setParameter("usuario", usuarioLogado);
            List<ClassificacaoFinanca> cfs = query.getResultList();
            return cfs != null && !cfs.isEmpty() ? cfs : null;
        } catch (Exception e) {
            log.error("Ocorreu um erro", e);
            throw e;
        } finally {
            entityManager.close();
            logSaida();
        }
    }

    public ClassificacaoFinanca recuperaSuperiorPorObjetoId(String idClassificacao, Usuario usuarioLogado) throws Exception {
        logEntrada("recuperaSuperiorPorObjetoId");
        try {
            entityManager = JPADAOFactory.createEntityManager();

            entityManager.getTransaction().begin();
            TypedQuery<ClassificacaoFinanca> query = entityManager.createNamedQuery("ClassificacaoFinanca.findSuperiorById",
                    ClassificacaoFinanca.class).setParameter("id", idClassificacao).setParameter("usuario", usuarioLogado);
            ClassificacaoFinanca cf = query.getSingleResult();
            return cf != null ? cf : null;
        } catch (Exception e) {
            log.error("Ocorreu um erro", e);
            throw e;
        } finally {
            entityManager.close();
            logSaida();
        }
    }

    public void exclui(ClassificacaoFinanca cf) throws Exception {
        logEntrada("exclui");
        try {
            entityManager = JPADAOFactory.createEntityManager();
            entityManager.getTransaction().begin();

            if (cf == null) {
                throw new NullPointerException("Classificacao Invalida.");
            }

            cf = entityManager.merge(cf);
            entityManager.remove(cf);
            entityManager.getTransaction().commit();

        }catch (Exception e) {
            log.error("Ocorreu um erro na exclusão da classificacao. Método: exclui", e);

            entityManager.getTransaction().rollback();

            throw e;
        }finally {
            entityManager.close();
        }
        logSaida();
    }
}
