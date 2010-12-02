package usuario.ctr.mb;

import androd.helper.AndRodHelper;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import usuario.facade.UsuarioFacadeImpl;
import usuario.to.Usuario;

public class UsuarioAutenticaMB {

    private String login;
    private String senha;
    
    private UsuarioFacadeImpl usuarioFacade = null;

    public UsuarioFacadeImpl getUsuarioFacade() {

        if (usuarioFacade == null) {
            usuarioFacade = new UsuarioFacadeImpl();
        }
        return usuarioFacade;
    }

    public ResourceBundle getBundle() {
        return ResourceBundle.getBundle(
                FacesContext.getCurrentInstance().getApplication().getMessageBundle(),
                FacesContext.getCurrentInstance().getViewRoot().getLocale());
    }

    /** Creates a new instance of UsuarioMB */
    public UsuarioAutenticaMB() {
    }

    /*
     * Método responsável por invocar métodos que realizam a inserção de um registro turma do Banco de dados
     */
    public String autenticaLogin() {


        try {

            if(AndRodHelper.ehNuloBranco(login) || AndRodHelper.ehNuloBranco(senha)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                                                                        FacesMessage.SEVERITY_ERROR,
                                                                        "Favor informar Login e Senha!",
                                                                        "Favor informar Login e Senha!"));

                return null;
            }

            Usuario usuario = new Usuario(login, senha);

            usuario = getUsuarioFacade().recuperaUsuarioPorLoginESenha(usuario);

            if (usuario != null) {

                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                
                session.setAttribute("usuarioLogado", usuario);
                session.isNew();

                return "logado";
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                                                                        FacesMessage.SEVERITY_ERROR,
                                                                        getBundle().getString("mensagem_erro_login_senha_incorretos"),
                                                                        getBundle().getString("mensagem_erro_login_senha_incorretos")));
            
        } catch (Exception ex) {
            
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                                                                        FacesMessage.SEVERITY_ERROR,
                                                                        getBundle().getString("mensagem_erro_login_senha_incorretos"),
                                                                        getBundle().getString("mensagem_erro_login_senha_incorretos")));
        }

        return "deslogado";
    }
    
    /*
     * Método responsável por invocar métodos que realizam a inserção de um registro turma do Banco de dados
     */
    public String autenticaLogout() {

        try {

            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

            Usuario usuario = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");

            usuario = getUsuarioFacade().recuperaUsuario(usuario);

            usuario.setDataUltimoAcesso(Calendar.getInstance().getTime());

            getUsuarioFacade().alteraUsuario(usuario);

            session.setAttribute("usuarioLogado", null);
            session.removeAttribute("usuarioLogado");
            session.invalidate();

        } catch (Exception ex) {

            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ex.getMessage()));
        }

        return "deslogado";
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = AndRodHelper.criptografaSenha(senha);
    }
}
