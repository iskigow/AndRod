package financa.ctr.mb;

import androd.ctr.mb.BaseMB;
import financa.to.ClassificacaoFinanca;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author iskigow
 */
public class ClassificacaoFinancaMB extends BaseMB {

    private ClassificacaoFinanca cf;
    private String MODO = "novo";

    /** Creates a new instance of ClassificacaoFinancaMB */
    public ClassificacaoFinancaMB() {
        MODO = "novo";
    }

    public String novo() {
        MODO = "novo";
        cf = null;
        return "classificacaoman";
    }

    public String editar() {
        try {
            String idClassificacao = (String) getParameterMap().get("idClassificacao");
            cf = new ClassificacaoFinanca();
            cf.setId(idClassificacao);
            cf = getFinancaFacade().recuperaClassificacaoFinancaPorId(cf, getUsuarioLogado());

        } catch (Exception e) {

            Logger.getLogger(ClassificacaoFinanca.class.getName()).log(Level.SEVERE, null, e);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possível editar!", "Não foi possível editar!"));

        }
        setMODO("edicao");
        return "classificacaoman";
    }

    public void gravar() {

        try {
            if ("novo".equals(MODO)) {
                getFinancaFacade().incluiClassificacaoFinanca(cf);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getBundle().getString("classificacaoFinancaIncluidaSucesso"), getBundle().getString("classificacaoFinancaIncluidaSucesso")));
            } else {
                getFinancaFacade().alteraClassificacaoFinanca(cf);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getBundle().getString("classificacaoFinancaAlteradaSucesso"), getBundle().getString("classificacaoFinancaAlteradaSucesso")));
            }
            MODO = "edicao";

        } catch (Exception e) {
                Logger.getLogger(ClassificacaoFinanca.class.getName()).log(Level.SEVERE, null, e);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
        }

    }

    public void buscaSuperior(ValueChangeEvent event) {

        PhaseId phaseId = event.getPhaseId();
 
        if (phaseId.equals(PhaseId.ANY_PHASE)) {
            event.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
            if (event.getNewValue() != null && !((String) event.getNewValue()).isEmpty()) {
                getSuperior().setId((String) event.getNewValue());
                buscaSuperior();
            }
        }
    }

    public void buscaSuperior() {
        ClassificacaoFinanca superior = getSuperior();
        try {
            superior = getFinancaFacade().recuperaClassificacaoFinancaPorId(superior,getUsuarioLogado());
            
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ClassificacaoFinancaMB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        if (superior != null)
            cf.setSuperior(superior);
        else {
            cf.setSuperior(null);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Classificação superior não cadastrada!", "Classificação superior não cadastrada!"));
        }
    }

    private void initialCondiction() {
        if (cf == null) {
            cf = new ClassificacaoFinanca();
            cf.setUsuario(getUsuarioLogado());
        }
    }

    private void initialCondictionSuperior() {
        initialCondiction();
        if (cf.getSuperior() == null) {
            cf.setSuperior(new ClassificacaoFinanca());
        }
    }

    public String getIdCF() {
        initialCondiction();
        return cf.getId();
    }

    public void setIdCF(final String id) {
        initialCondiction();
        cf.setId(id);
    }

    public String getDescricao() {
        initialCondiction();
        return cf.getDescricao();
    }

    public void setDescricao(final String descricao) {
        initialCondiction();
        cf.setDescricao(descricao);
    }

    public String getDescricaoResumida() {
        initialCondiction();
        return cf.getDescricaoResumida();
    }

    public void setDescricaoResumida(final String descricaoResumida) {
    }

    public ClassificacaoFinanca getSuperior() {
        initialCondiction();
        return cf.getSuperior();
    }

    public void setSuperior(final ClassificacaoFinanca superior) {
        initialCondiction();
        cf.setSuperior(superior);
    }

    public String getIdSuperior() {
        initialCondictionSuperior();
        return cf.getSuperior().getId();
    }

    public void setIdSuperior(final String idSuperior) {
        initialCondictionSuperior();
        cf.getSuperior().setId(idSuperior);
    }

    public String getDescricaoResumidaSuperior() {
        initialCondictionSuperior();
        return cf.getSuperior().getDescricaoResumida();
    }

    public String getMODO() {
        return MODO;
    }

    public void setMODO(String MODO) {
        this.MODO = MODO;
    }
}
