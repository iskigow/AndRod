/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package financa.facade;

import financa.to.ClassificacaoFinanca;
import financa.to.Financa;
import financa.to.TipoFinanca;
import java.util.List;
import usuario.to.Usuario;

/**
 *
 * @author iskigow
 */
public interface IFinancaFacade {

    public void incluiClassificacaoFinanca(ClassificacaoFinanca classificacaoFinanca) throws Exception;

    public void alteraClassificacaoFinanca(ClassificacaoFinanca classificacaoFinanca) throws Exception;

    public ClassificacaoFinanca recuperaClassificacaoFinancaPorId(ClassificacaoFinanca superior, Usuario usuario) throws Exception;

    public List<ClassificacaoFinanca> recuperaTodasClassificacoesFinancas(Usuario usuario) throws Exception;

    public List<ClassificacaoFinanca> recuperaTodosUltimoNivelClassificacoesFinancas(Usuario usuarioLogado) throws Exception;

    public ClassificacaoFinanca recuperaSuperiorPorClassificacaoId(String idClassificacao, Usuario usuarioLogado) throws Exception;

    public void excluir(ClassificacaoFinanca cf) throws Exception;

    public void incluiFinanca(Financa financa) throws Exception;

    public void alteraFicacaoFinanca(Financa financa) throws Exception;

    public List<Financa> recuperaTodasFinancas(Usuario usuarioLogado) throws Exception;

    public List<Financa> recuperaTodasFinancasPorTipoFinanca(TipoFinanca tipoFinanca, Usuario usuarioLogado) throws Exception;

    public Financa recuperaFinancaPorId(Financa financa) throws Exception;

    public void excluir(Financa f) throws Exception;
}
