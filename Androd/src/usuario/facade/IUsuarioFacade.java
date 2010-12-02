package usuario.facade;

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
public interface IUsuarioFacade {


    void validaObrigatoriedadeUsuario(Usuario usuario) throws Exception;

    void validaObrigatoriedadeDependente(Dependente dependente) throws Exception;

    void incluiUsuario(Usuario usuario) throws Exception;

    void alteraUsuario(Usuario usuario) throws Exception;

    Usuario recuperaUsuario(Usuario usuario) throws Exception;

    Usuario recuperaUsuarioPorLogin(Usuario usuario) throws Exception;

    void excluiDependente(Dependente dependente) throws Exception;

    void incluiDependente(Dependente dependente) throws Exception;

    void alteraDependente(Dependente dependente) throws Exception;

    Dependente recuperaDependente(Dependente dependente) throws Exception;

    List<Dependente> recuperaTodosDependentes(Usuario usuario) throws Exception;

    void validaInclusaoAlteracao(Usuario usuario) throws Exception;

    void validaSenhaAlteracao(Usuario usuario) throws Exception;
}