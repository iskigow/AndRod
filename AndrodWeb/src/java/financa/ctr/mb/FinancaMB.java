/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package financa.ctr.mb;

import androd.ctr.mb.BaseMB;
import financa.to.ClassificacaoFinanca;
import financa.to.Financa;
import financa.to.TipoFinanca;
import java.math.BigDecimal;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Rodrigo G. M. Catto
 */
public class FinancaMB extends BaseMB {

    private Financa financa;
    private String MODO = "novo";


    /** Creates a new instance of FinancaMB */
    public FinancaMB() {
        super();
        MODO = "novo";
    }

    public String novo() {
        MODO = "novo";
        financa = null;
        return "financaman";
    }

    public String editar() {
        try {
            Long idFinanca = (Long) getParameterMap().get("idFinanca");
            financa = new Financa(idFinanca,getUsuarioLogado());
            financa = getFinancaFacade().recuperaFinancaPorId(financa);
            setMODO("edicao");
        } catch (Exception e) {

            Logger.getLogger(ClassificacaoFinanca.class.getName()).log(Level.SEVERE, null, e);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problema ao recuperar a Financa para edição", "Problema ao recuperar a Financa para edição"));

        }
        return "manterfinancaman";
    }

    public void gravar() {

        try {
            if ("novo".equals(MODO)) {
                getFinancaFacade().incluiFinanca(financa);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getBundle().getString("financaIncluidaSucesso"), getBundle().getString("financaIncluidaSucesso")));
            } else {
                getFinancaFacade().alteraFicacaoFinanca(financa);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getBundle().getString("financaAlteradaSucesso"), getBundle().getString("financaAlteradaSucesso")));
            }
            MODO = "edicao";

        } catch (Exception e) {
                Logger.getLogger(ClassificacaoFinanca.class.getName()).log(Level.SEVERE, null, e);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
        }

    }

    private void initialCondiction() {
        if (financa == null) {
            financa = new Financa(getUsuarioLogado());
        }
    }

    public BigDecimal getValor() {
        initialCondiction();
        return financa.getValor();
    }

    public ClassificacaoFinanca getClassificacaoFinanaca() {
        initialCondiction();
        return financa.getClassificacaoFinanca();
    }

    public Date getDataFinanca() {
        initialCondiction();
        return financa.getDataFinanca();
    }

    public String getDescricao() {
        initialCondiction();
        return financa.getDescricao();
    }

    public TipoFinanca getTipoFinanca() {
        initialCondiction();
        return financa.getTipoFinanca();
    }

    public Long getId() {
        initialCondiction();
        return financa.getId();
    }

    public void setId(Long id) {
        initialCondiction();
        financa.setId(id);
    }

    public String getMODO() {
        return MODO;
    }

    public void setMODO(String MODO) {
        this.MODO = MODO;
    }

    public TipoFinanca getCredito() {
        return TipoFinanca.CREDITO;
    }

    public TipoFinanca getDebito() {
        return TipoFinanca.DEBITO;
    }

    public void setTipoFinanca(TipoFinanca tipoFinanca) {
        initialCondiction();
        financa.setTipoFinanca(tipoFinanca);
    }

    public void setDescricao(String descricao) {
        initialCondiction();
        financa.setDescricao(descricao);
    }

    public void setDataFinanca(Date dataFinanca) {
        initialCondiction();
        financa.setDataFinanca(dataFinanca);
    }

    public void setValor(BigDecimal valor) {
        initialCondiction();
        financa.setValor(valor);
    }

}
