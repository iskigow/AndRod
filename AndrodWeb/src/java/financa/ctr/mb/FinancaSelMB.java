/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package financa.ctr.mb;

import androd.ctr.mb.BaseMB;
import financa.to.Financa;
import financa.to.TipoFinanca;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


/**
 *
 * @author iskigow
 */
public class FinancaSelMB extends BaseMB {

    private List<Financa> financas;

    /** Creates a new instance of FinancaSelMB */
    public FinancaSelMB() {
        super();
        if (financas == null) {
            recuperaTodasFinancas();
        }
    }

    private void recuperaTodasFinancas() {
        try {

            financas = getFinancaFacade().recuperaTodasFinancas(getUsuarioLogado());

        } catch (Exception e) {

            Logger.getLogger(Financa.class.getName()).log(Level.SEVERE, null, e);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problema na listagem das Finanças", "Problema na listagem das Finanças"));

        }
    }

    public String excluir() {
        Financa f;
        try {
            Long idFinanca = Long .valueOf((String) getParameterMap().get("idFinanca"));
            f = new Financa(idFinanca,getUsuarioLogado());
            f = getFinancaFacade().recuperaFinancaPorId(f);
            getFinancaFacade().excluir(f);

        } catch (Exception e) {

            Logger.getLogger(Financa.class.getName()).log(Level.SEVERE, null, e);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não é possível exluir esta Finança!", "Não é possível exluir esta Finança!"));

        }
        recuperaTodasFinancas();
        return "";
    }

    private void recuperaTodasFinancasPorTipoFinanca(TipoFinanca tipoFinanca) {
        try {

            financas = getFinancaFacade().recuperaTodasFinancasPorTipoFinanca(tipoFinanca,getUsuarioLogado());

        } catch (Exception e) {

            Logger.getLogger(Financa.class.getName()).log(Level.SEVERE, null, e);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problema na listagem das Finanças", "Problema na listagem das Finanças"));

        }
    }

    public String listarTodasFinancas() {
        recuperaTodasFinancas();
        return "financasel";
    }

    public List<Financa> getFinancas() {
        return financas;
    }

    public void setFinancas(List<Financa> financas) {
        this.financas = financas;
    }
}
