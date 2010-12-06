/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package financa.facade;

import financa.dao.ClassificacaoFinancaDAO;
import financa.dao.FinancaDAO;
import financa.to.ClassificacaoFinanca;
import financa.to.Financa;
import financa.to.TipoFinanca;
import java.util.List;
import usuario.to.Usuario;

/**
 *
 * @author iskigow
 */
public class FinancaFacadeImpl implements IFinancaFacade {

    private ClassificacaoFinancaDAO classificacaoDAO;
    private FinancaDAO financaDAO;

    protected ClassificacaoFinancaDAO getClassificacaoDAO() {
        if (classificacaoDAO == null) {
            classificacaoDAO = new ClassificacaoFinancaDAO();
        }
        return classificacaoDAO;
    }

    public FinancaDAO getFinancaDAO() {
        if (financaDAO == null) {
            financaDAO = new FinancaDAO();
        }
        return financaDAO;
    }

    public void incluiClassificacaoFinanca(ClassificacaoFinanca classificacaoFinanca) throws Exception {
        getClassificacaoDAO().inclui(classificacaoFinanca);
    }

    public void alteraClassificacaoFinanca(ClassificacaoFinanca classificacaoFinanca) throws Exception {
        getClassificacaoDAO().altera(classificacaoFinanca);
    }

    public ClassificacaoFinanca recuperaClassificacaoFinancaPorId(ClassificacaoFinanca superior, Usuario usuario) throws Exception {
        return getClassificacaoDAO().recuperaObjetoPorId(superior,usuario);
    }

    public List<ClassificacaoFinanca> recuperaTodasClassificacoesFinancas(Usuario usuario) throws Exception {
        return getClassificacaoDAO().recuperaObjetos(usuario);
    }

    public List<ClassificacaoFinanca> recuperaTodosUltimoNivelClassificacoesFinancas(Usuario usuarioLogado) throws Exception {
        return getClassificacaoDAO().recuperaObjetosUltimoNivel(usuarioLogado);
    }

    public ClassificacaoFinanca recuperaSuperiorPorClassificacaoId(String idClassificacao, Usuario usuarioLogado) throws Exception {
        return getClassificacaoDAO().recuperaSuperiorPorObjetoId(idClassificacao, usuarioLogado);
    }

    public Financa recuperaFinancaPorId(Financa financa) throws Exception {
        return getFinancaDAO().recuperaObjetoPorId(financa);
    }

    public void excluir(ClassificacaoFinanca cf) throws Exception {
        getClassificacaoDAO().exclui(cf);
    }

    public void incluiFinanca(Financa financa) throws Exception {
        getFinancaDAO().inclui(financa);
    }

    public void alteraFicacaoFinanca(Financa financa) throws Exception {
        getFinancaDAO().altera(financa);
    }

    public List<Financa> recuperaTodasFinancas(Usuario usuarioLogado) throws Exception {
        return getFinancaDAO().recuperaObjetos(usuarioLogado);
    }

    public List<Financa> recuperaTodasFinancasPorTipoFinanca(TipoFinanca tipoFinanca, Usuario usuarioLogado) throws Exception {
        return getFinancaDAO().recuperaObjetos(tipoFinanca,usuarioLogado);
    }

    public void excluir(Financa f) throws Exception {
        getFinancaDAO().exclui(f);
    }
}
