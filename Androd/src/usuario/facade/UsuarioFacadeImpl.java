package usuario.facade;

import usuario.bo.DependenteBO;
import usuario.bo.UsuarioBO;
import usuario.dao.DependenteDAO;
import androd.dao.JPADAOFactory;
import usuario.dao.UsuarioDAO;
import java.util.List;
import usuario.to.Dependente;
import usuario.to.Usuario;

/**
 *
 * Fachada que simplifica o acesso pelos clientes.
 *
 * Faz o papel de um Front Controller, pois o acesso é centralizado
 *
 * @author andrevmdb
 */
public class UsuarioFacadeImpl implements IUsuarioFacade{

    /** Usuário **/
    private UsuarioBO usuarioBO;
    private UsuarioDAO usuarioDAO;

    /** Dependente **/
    private DependenteBO dependenteBO;
    private DependenteDAO dependenteDAO;

    public UsuarioFacadeImpl() {
        JPADAOFactory.createEntityManagerFactory();
    }

    private UsuarioDAO getUsuarioDAO() {

        if(usuarioDAO == null){
            usuarioDAO = new UsuarioDAO();
        }

        return usuarioDAO;
    }

    private UsuarioBO getUsuarioBO() {

        if(usuarioBO == null){
            usuarioBO = new UsuarioBO();
        }

        return usuarioBO;
    }

    private DependenteBO getDependenteBO() {

        if(dependenteBO == null){
            dependenteBO = new DependenteBO();
        }

        return dependenteBO;
    }

    private DependenteDAO getDependenteDAO() {

        if(dependenteDAO == null){
            dependenteDAO = new DependenteDAO();
        }

        return dependenteDAO;
    }

    /** Usuario **/
    public void incluiUsuario(Usuario usuario) throws Exception{
        getUsuarioDAO().inclui(usuario);
    }

    public void alteraUsuario(Usuario usuario) throws Exception{
        getUsuarioDAO().altera(usuario);
    }

    public Usuario recuperaUsuario(Usuario usuario) throws Exception{
        return getUsuarioDAO().recuperaObjeto(usuario);
    }

    public Usuario recuperaUsuarioPorLogin(Usuario usuario) throws Exception{
        return getUsuarioDAO().recuperaUsuarioPorLogin(usuario);
    }

    public Usuario recuperaUsuarioPorLoginESenha(Usuario usuario) throws Exception{
        return getUsuarioDAO().recuperaUsuarioPorLoginESenha(usuario);
    }

    public void validaInclusaoAlteracao(Usuario usuario) throws Exception{
        getUsuarioBO().validaInclusaoAlteracao(usuario);
    }

    public void validaSenhaAlteracao(Usuario usuario) throws Exception {
        getUsuarioBO().validaSenhaAlteracao(usuario);
    }

    /** Dependente **/
    public void excluiDependente(Dependente dependente) throws Exception{
        getDependenteDAO().exclui(dependente);
    }

    public void incluiDependente(Dependente dependente) throws Exception{
        getDependenteDAO().inclui(dependente);
    }

    public void alteraDependente(Dependente dependente) throws Exception{
        getDependenteDAO().altera(dependente);
    }

    public Dependente recuperaDependente(Dependente dependente) throws Exception{
        return getDependenteDAO().recuperaObjeto(dependente);
    }

    public List<Dependente> recuperaTodosDependentes(Usuario usuario) throws Exception{
        return getDependenteDAO().recuperaDependentesPorLoginUsuario(usuario);
    }

    public void validaObrigatoriedadeUsuario(Usuario usuario) throws Exception {
        getUsuarioBO().validaObrigatorios(usuario);
    }

    public void validaObrigatoriedadeDependente(Dependente dependente) throws Exception {
        getDependenteBO().validaObrigatorios(dependente);
    }
}