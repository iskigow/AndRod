package usuario.ctr.mb;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import usuario.to.Dependente;
import usuario.to.Usuario;

public class DependenteMB extends BaseMB{

    private String idDependente;
    private String nomeResumido;
    private String nomeCompleto;
    private String cpf;

    public DependenteMB(){
    }

    public String novoDependente() {

        setIdDependente("");
        setNomeCompleto("");
        setNomeResumido("");
        setCpf("");

        return "dependenteman";
    }

    /*
     * Método responsável por invocar métodos que realizam a inserção de um registro turma do Banco de dados
     */
    public void incluir() {

        try {

            /** Inclusão dependente **/

            Usuario usuario = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");

            Dependente dependente = new Dependente(nomeResumido, nomeCompleto, cpf, usuario);

            getUsuarioFacade().incluiDependente(dependente);

            setIdDependente(dependente.getId().toString());

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getBundle().getString("dependenteIncluidoSucesso"), getBundle().getString("dependenteIncluidoSucesso")));

        } catch (Exception ex) {

            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, getBundle().getString("dependenteIncluidoFalha"), getBundle().getString("dependenteIncluidoFalha")));
        }
    }

    /*
     * Método responsável por invocar métodos que realizam a inserção de um registro turma do Banco de dados
     */
    public String editar() {

        try {

            Map map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

            Dependente dependente = new Dependente(Long.valueOf((String) map.get("idDependente")));

            dependente = getUsuarioFacade().recuperaDependente(dependente);

            if(dependente != null){
                setIdDependente(dependente.getId().toString());
                setNomeCompleto(dependente.getNomeCompleto());
                setNomeResumido(dependente.getNomeResumido());
                setCpf(dependente.getCpf());
            }

        } catch (Exception ex) {

            Logger.getLogger(Dependente.class.getName()).log(Level.SEVERE, null, ex);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ex.getMessage()));
        }

        return "dependenteman";
    }
    
    /*
     * Método responsável por invocar métodos que realizam a inserção de um registro turma do Banco de dados
     */
    public void alterar() {

        try {

            /** Alteração dependente **/
            Dependente dependente = new Dependente(Long.valueOf(idDependente));

            Dependente dependenteExistente = getUsuarioFacade().recuperaDependente(dependente);

            dependenteExistente.setCpf(cpf);
            dependenteExistente.setNomeCompleto(nomeCompleto);
            dependenteExistente.setNomeResumido(nomeResumido);

            getUsuarioFacade().alteraDependente(dependenteExistente);

            setIdDependente(dependenteExistente.getId().toString());

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getBundle().getString("dependenteAlteradoSucesso"), getBundle().getString("dependenteAlteradoSucesso")));

        } catch (Exception ex) {

            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, getBundle().getString("dependenteAlteradoFalha"), getBundle().getString("dependenteAlteradoFalha")));
        }
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getNomeResumido() {
        return nomeResumido;
    }

    public void setNomeResumido(String nomeResumido) {
        this.nomeResumido = nomeResumido;
    }

    public String getIdDependente() {
        return idDependente;
    }

    public void setIdDependente(String idDependente) {
        this.idDependente = idDependente;
    }
}