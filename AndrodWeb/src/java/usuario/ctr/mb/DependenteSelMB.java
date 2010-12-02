/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package usuario.ctr.mb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import usuario.to.Dependente;
import usuario.to.Usuario;

/**
 *
 * @author andrevmdb
 */
public class DependenteSelMB extends BaseMB{

    private List<Dependente> listaDependentes = new ArrayList<Dependente>();

    public String listaDependente() {

        try {

            Usuario usuario = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");

            listaDependentes = getUsuarioFacade().recuperaTodosDependentes(usuario);

            setListaDependentes(listaDependentes);

            if(listaDependentes == null || listaDependentes.isEmpty()){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Não foram encontrados dependentes cadastrados!", "Não foram encontrados dependentes cadastrados!"));
            }

        } catch (Exception ex) {

            Logger.getLogger(Dependente.class.getName()).log(Level.SEVERE, null, ex);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ex.getMessage()));
        }

        return "dependentesel";
    }

    /*
     * Método responsável por invocar métodos que realizam a inserção de um registro turma do Banco de dados
     */
    public void excluir() {

        try {

            Map map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

            String idDependente = (String) map.get("idDependente");

            Dependente dependente = new Dependente(Long.valueOf(idDependente));

            dependente = getUsuarioFacade().recuperaDependente(dependente);

            getUsuarioFacade().excluiDependente(dependente);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getBundle().getString("dependenteExcluidoSucesso"), getBundle().getString("dependenteExcluidoSucesso")));

            listaDependente();

        } catch (Exception ex) {

            Logger.getLogger(Dependente.class.getName()).log(Level.SEVERE, null, ex);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ex.getMessage()));
        }
    }

    public List<Dependente> getListaDependentes() {
        return listaDependentes;
    }

    public void setListaDependentes(List<Dependente> listaDependentes) {
        this.listaDependentes = listaDependentes;
    }
}