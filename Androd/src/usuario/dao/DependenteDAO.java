package usuario.dao;

import androd.dao.AndrodDAO;
import androd.dao.JPADAOFactory;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import usuario.to.Dependente;
import usuario.to.Usuario;

public class DependenteDAO implements AndrodDAO<Dependente> {

    private static Logger log = Logger.getLogger(DependenteDAO.class);

    private EntityManager entityManager = null;

    /**
     * Recupera todos os dependentes cadastrados.
     *
     * @return List<Dependente>
     */
    public List<Dependente> recuperaObjetos() throws Exception {

        try {

            entityManager = JPADAOFactory.createEntityManager();

            entityManager.getTransaction().begin();

            List<Dependente> usuarios = entityManager.createQuery("SELECT * FROM Dependente order by id").getResultList();

            return usuarios;

        } catch (Exception e) {

            log.error("Ocorreu um erro na recuperação de todos os dependentes. Método: recuperaObjetos", e);

            throw e;

        } finally {
            entityManager.close();
        }
    }

    /**
     *
     * Recupera dependente conforme parâmetro dependente passado.
     *
     * @return
     * @throws Exception
     */
    public Dependente recuperaObjeto(Dependente dependente) throws Exception {

        try {

            entityManager = JPADAOFactory.createEntityManager();

            entityManager.getTransaction().begin();

            if (dependente == null) {
                throw new NullPointerException("Dependente inválido.");
            }

            dependente = entityManager.find(Dependente.class, dependente.getId());

            
            return dependente;

        } catch (Exception e) {

            log.error("Ocorreu um erro na recuperação do dependente. Método: recuperaObjeto", e);

            throw e;

        } finally {
            entityManager.close();
        }
    }

    /**
     *
     * Recupera todos os dependentes do usuário corrente.
     *
     * @return
     * @throws Exception
     */
    public List<Dependente> recuperaDependentesPorLoginUsuario(Usuario usuario) throws Exception {

        try {

            entityManager = JPADAOFactory.createEntityManager();

            entityManager.getTransaction().begin();

            if (usuario == null) {
                throw new NullPointerException("Usuário inválido.");
            }

            Query query = entityManager.createNamedQuery("Dependente.findByLoginUsuario").setParameter("login", usuario.getLogin());

            List<Dependente> dependentes = query.getResultList();

            return dependentes != null && !dependentes.isEmpty() ? dependentes : Collections.EMPTY_LIST;

        } catch (Exception e) {

            log.error("Ocorreu um erro na recuperação dos dependentes por usuário. Método: recuperaDependentesPorUsuario", e);

            throw e;

        } finally {
            entityManager.close();
        }
    }

    /**
     *
     * Inclusão de um novo dependente
     *
     * @param dependente
     * @throws Exception
     */
    public void inclui(Dependente dependente) throws Exception {

        try {

            entityManager = JPADAOFactory.createEntityManager();

            entityManager.getTransaction().begin();

            if (dependente == null) {
                throw new NullPointerException("Dependente inválido.");
            }

            entityManager.persist(dependente);
            entityManager.getTransaction().commit();

        } catch (Exception e) {

            log.error("Ocorreu um erro na inclusão do dependente. Método: inclui", e);

            /** Desfaz possíveis gatilhos que estão na sessão. **/
            entityManager.getTransaction().rollback();

            throw e;

        } finally {
            entityManager.close();
        }
    }

    /**
     *
     * Alteração do dependente passado como parâmetro.
     *
     * @param entidadeInstancia
     * @throws Exception
     */
    public void altera(Dependente dependente) throws Exception {

        try {

            entityManager = JPADAOFactory.createEntityManager();

            entityManager.getTransaction().begin();

            if (dependente == null) {
                throw new NullPointerException("Dependente inválido.");
            }

            entityManager.merge(dependente);
            
            entityManager.getTransaction().commit();

        } catch (Exception e) {

            log.error("Ocorreu um erro na alteração do dependente. Método: altera", e);

            e.printStackTrace();
            
            entityManager.getTransaction().rollback();

            throw e;

        } finally {
            entityManager.close();
        }
    }

    /**
     *
     * Exclusão do dependente passado como parâmetro.
     *
     * @param entidadeInstancia
     * @throws Exception
     */
    public void exclui(Dependente dependente) throws Exception {

        try {

            entityManager = JPADAOFactory.createEntityManager();

            entityManager.getTransaction().begin();

            if (dependente == null) {
                throw new NullPointerException("Dependente inválido.");
            }

            dependente = entityManager.merge(dependente);
            entityManager.remove(dependente);
            entityManager.getTransaction().commit();

        } catch (Exception e) {

            log.error("Ocorreu um erro na exclusão do dependente. Método: exclui", e);

            entityManager.getTransaction().rollback();

            throw e;

        } finally {
            entityManager.close();
        }
    }
}