/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package financa.ctr.mb;

import androd.ctr.mb.BaseMB;
import financa.to.ClassificacaoFinanca;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


/**
 *
 * @author iskigow
 */
public class ClassificacaoFinancaSelMB extends BaseMB {

    private List<ClassificacaoFinanca> classificacoes;
    private String superior;

    /** Creates a new instance of ClassificacaoFinancaSelMB */
    public ClassificacaoFinancaSelMB() {
        super();
        superior = null;
        if (classificacoes == null) {
            recuperaTodosUltimoNivelClassificacoesFinancas();
        }
    }

    private void recuperaTodasClassificacoesFinancas() {
        try {

            classificacoes = getFinancaFacade().recuperaTodasClassificacoesFinancas(getUsuarioLogado());

        } catch (Exception e) {

            Logger.getLogger(ClassificacaoFinanca.class.getName()).log(Level.SEVERE, null, e);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problema na listagem das Classificações", "Problema na listagem das Classificações"));

        }
    }

    private void recuperaTodosUltimoNivelClassificacoesFinancas() {
        try {

            classificacoes = getFinancaFacade().recuperaTodosUltimoNivelClassificacoesFinancas(getUsuarioLogado());

        } catch (Exception e) {

            Logger.getLogger(ClassificacaoFinanca.class.getName()).log(Level.SEVERE, null, e);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problema na listagem das Classificações que são Ultimo Nível", "Problema na listagem das Classificações que são Ultimo Nível"));

        }
    }

    private void recuperaSuperior () {
        try {

            String idClassificacao = (String) getParameterMap().get("idClassificacao");
            ClassificacaoFinanca classificacaoSuperior = getFinancaFacade().recuperaSuperiorPorClassificacaoId(idClassificacao, getUsuarioLogado());

            if (classificacaoSuperior !=  null) {
                this.superior = classificacaoSuperior.superiorInfo();
            } else {
                this.superior = "Não possui classificação superior";
            }

        } catch (Exception e) {

            Logger.getLogger(ClassificacaoFinanca.class.getName()).log(Level.SEVERE, null, e);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problema na listagem das Classificações que são Ultimo Nível", "Problema na listagem das Classificações que são Ultimo Nível"));

        }
    }

    public String excluir() {
        ClassificacaoFinanca cf;
        try {
            String idClassificacao = (String) getParameterMap().get("idClassificacao");
            cf = new ClassificacaoFinanca();
            cf.setId(idClassificacao);
            cf = getFinancaFacade().recuperaClassificacaoFinancaPorId(cf, getUsuarioLogado());
            getFinancaFacade().excluir(cf);

        } catch (Exception e) {

            Logger.getLogger(ClassificacaoFinanca.class.getName()).log(Level.SEVERE, null, e);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não é possível exluir esta Classificação!", "Não é possível exluir esta Classificação!"));

        }
        recuperaTodasClassificacoesFinancas();
        return "";
    }

    public String ultimoNivel() {
        recuperaTodosUltimoNivelClassificacoesFinancas();
        return "";
    }

    public String todos() {
        recuperaTodasClassificacoesFinancas();
        return "";
    }

    public List<ClassificacaoFinanca> getClassificacoes() {
        return classificacoes;
    }

    public String getSuperior() {
        return superior;
    }

    public void mostraInfoSuperior() {
           recuperaSuperior();
    }
}