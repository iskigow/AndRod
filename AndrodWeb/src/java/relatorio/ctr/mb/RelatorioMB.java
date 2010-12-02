/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package relatorio.ctr.mb;

import androd.ctr.mb.BaseMB;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import usuario.to.Usuario;

/**
 *
 * @author andrevmdb
 */
public class RelatorioMB extends BaseMB {

    public void financasUsuario() {

        try {

            Usuario usuario = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");

            String urlRelatorio =
                    "/birt/run?__report=rel/usuariofinancasrel.rptdesign&__format=pdf&idusuario=" + usuario.getId();

            HttpServletRequest httpRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

            HttpServletResponse httpResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

            httpResponse.sendRedirect("http://" + httpRequest.getServerName() + ":" + httpRequest.getLocalPort() + urlRelatorio);

        } catch (Exception ex) {

            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao invocar Relatório usuário.", "Falha ao invocar Relatório usuário."));
        }
    }

    public void usuarioDependentesRelatorio() {

        try {

            Usuario usuario = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");

            String urlRelatorio =
                    "/birt/run?__report=rel/usuariodependentes.rptdesign&__format=pdf&idusuario=" + usuario.getId();

            HttpServletRequest httpRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

            HttpServletResponse httpResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

            httpResponse.sendRedirect("http://" + httpRequest.getServerName() + ":" + httpRequest.getLocalPort() + urlRelatorio);

        } catch (Exception ex) {

            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao invocar Relatório usuário.", "Falha ao invocar Relatório usuário."));
        }
    }
}