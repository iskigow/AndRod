/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package usuario.ctr.mb;

import financa.facade.FinancaFacadeImpl;
import financa.facade.IFinancaFacade;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TimeZone;
import javax.faces.context.FacesContext;
import usuario.facade.IUsuarioFacade;
import usuario.facade.UsuarioFacadeImpl;
import usuario.to.Usuario;

/**
 *
 * @author iskigow
 */
public abstract class BaseMB {

    private IFinancaFacade financaFacade = null;

    private IUsuarioFacade usuarioFacade = null;

    public IFinancaFacade getFinancaFacade() {
        if (financaFacade == null)
            financaFacade = new FinancaFacadeImpl();
        return financaFacade;
    }

    public IUsuarioFacade getUsuarioFacade() {
        if (usuarioFacade == null)
            usuarioFacade = new UsuarioFacadeImpl();
        return usuarioFacade;
    }

    public ResourceBundle getBundle() {
        return ResourceBundle.getBundle(
                FacesContext.getCurrentInstance().getApplication().getMessageBundle(),
                FacesContext.getCurrentInstance().getViewRoot().getLocale());
    }

    protected Usuario getUsuarioLogado() {
        return (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
    }

    protected Map getParameterMap() {
            return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    }

    public TimeZone getTimeZone() {
        return TimeZone.getDefault();
    }
}