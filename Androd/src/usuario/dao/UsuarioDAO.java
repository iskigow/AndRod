package usuario.dao;

import androd.dao.AndrodDAO;
import androd.dao.JPADAOFactory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import usuario.to.Usuario;

public class UsuarioDAO implements AndrodDAO<Usuario> {

    private static Logger log = Logger.getLogger(UsuarioDAO.class);
    
    private EntityManager entityManager = null;

    public Usuario recuperaUsuarioPorLogin(Usuario usuario) throws Exception {

        try {

            entityManager = JPADAOFactory.createEntityManager();

            entityManager.getTransaction().begin();

            if (usuario == null) {
                throw new NullPointerException("Usuário inválido.");
            }

            Query query = entityManager.createNamedQuery("Usuario.findByLogin").setParameter("login", usuario.getLogin());

            List<Usuario> usuarios = query.getResultList();

            return usuarios != null && !usuarios.isEmpty() ? usuarios.get(0) : null;

        } catch (Exception e) {

            log.error("Ocorreu um erro na recuperação do usuário por login. Método: recuperaUsuarioPorLogin", e);

            throw e;

        } finally {
            entityManager.close();
        }
    }

    public Usuario recuperaUsuarioPorLoginESenha(Usuario usuario) throws Exception {

        try {

            entityManager = JPADAOFactory.createEntityManager();

            entityManager.getTransaction().begin();

            if (usuario == null) {
                throw new NullPointerException("Usuário inválido.");
            }

            Query query = entityManager.createNamedQuery("Usuario.findByLoginSenha")
                                                .setParameter("login", usuario.getLogin())
                                                .setParameter("senha", usuario.getSenha());

            List<Usuario> usuarios = query.getResultList();

            return usuarios != null && !usuarios.isEmpty() ? usuarios.get(0) : null;

        } catch (Exception e) {

            log.error("Ocorreu um erro na recuperação do usuário por login. Método: recuperaUsuarioPorLogin", e);

            throw e;

        } finally {
            entityManager.close();
        }
    }

    /**
     * Recupera todos os usuários cadastrados.
     *
     * @return List<Usuario>
     */
    public List<Usuario> recuperaObjetos() throws Exception {

        try {

            entityManager = JPADAOFactory.createEntityManager();

            entityManager.getTransaction().begin();

            List<Usuario> usuarios = entityManager. createQuery("SELECT * FROM Usuario order by id").getResultList();

            return usuarios;

        } catch (Exception e) {

            log.error("Ocorreu um erro na recuperação de todos os usuários. Método: recuperaObjetos", e);

            throw e;

        } finally {
            entityManager.close();
        }
    }

    /**
     * 
     * Recupera usuário conforme parâmetro usuário passado.
     *
     * @return
     * @throws Exception
     */
    public Usuario recuperaObjeto(Usuario usuario) throws Exception {

        try {

            entityManager = JPADAOFactory.createEntityManager();

            entityManager.getTransaction().begin();

            if (usuario == null) {
                throw new NullPointerException("Usuário inválido.");
            }

            usuario = entityManager.find(usuario.getClass(), usuario.getId());

            return usuario;

        } catch (Exception e) {

            log.error("Ocorreu um erro na recuperação do usuário. Método: recuperaObjeto", e);

            throw e;

        } finally {
            entityManager.close();
        }
    }

    public void inclui(Usuario usuario) throws Exception {

        try {

            entityManager = JPADAOFactory.createEntityManager();

            entityManager.getTransaction().begin();

            if (usuario == null) {
                throw new NullPointerException("Usuário inválido.");
            }

            entityManager.persist(usuario);

            entityManager.getTransaction().commit();

        } catch (Exception e) {

            log.error("Ocorreu um erro na inclusão do usuário. Método: inclui", e);

            /** Desfaz possíveis gatilhos que estão na sessão. **/
            entityManager.getTransaction().rollback();

            throw e;

        } finally {
            entityManager.close();
        }
    }

    /**
     *
     * Alteração do usuário passado como parâmetro.
     *
     * @param entidadeInstancia
     * @throws Exception
     */
    public void altera(Usuario usuario) throws Exception {

        try {

            entityManager = JPADAOFactory.createEntityManager();

            entityManager.getTransaction().begin();

            if (usuario == null) {
                throw new NullPointerException("Usuário inválido.");
            }

            entityManager.merge(usuario);
            entityManager.getTransaction().commit();

        } catch (Exception e) {

            log.error("Ocorreu um erro na alteração do usuário. Método: altera", e);

            entityManager.getTransaction().rollback();

            throw e;

        } finally {
            entityManager.close();
        }
    }

    /**
     *
     * Exlusão do usuário passado como parâmetro.
     *
     * @param entidadeInstancia
     * @throws Exception
     */
    public void exclui(Usuario usuario) throws Exception {

        try {

            entityManager = JPADAOFactory.createEntityManager();

            entityManager.getTransaction().begin();

            if (usuario == null) {
                throw new NullPointerException("Usuário inválido.");
            }
            
            entityManager.remove(usuario);
            entityManager.getTransaction().commit();

        } catch (Exception e) {

            log.error("Ocorreu um erro na exclusão do usuário. Método: exclui", e);

            entityManager.getTransaction().rollback();

            throw e;

        } finally {
            entityManager.close();
        }
    }
}