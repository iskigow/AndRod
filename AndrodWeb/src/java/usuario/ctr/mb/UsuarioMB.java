package usuario.ctr.mb;



import androd.ctr.mb.BaseMB;
import androd.helper.AndRodHelper;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import usuario.to.Usuario;


/**
 *
 * @author andrevmdb
 */
public class UsuarioMB extends BaseMB{

    private String idUsuario;
    private String login;
    private String senha;
    private String senhaAtual;
    private String confirmaSenha;
    private String nomeResumido;
    private String nomeCompleto;
    private String email;
    private String cpf;
    private Date dataAtual;
    private Date ultimoAcesso;

    public UsuarioMB() {

        Usuario usuario = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");

        setDataAtual(Calendar.getInstance().getTime());

        if(usuario != null){
            setUltimoAcesso(usuario.getDataUltimoAcesso());
            setNomeResumido(usuario.getNomeResumido());
        }
    }

    public String novoUsuario() {

        setIdUsuario("");
        setNomeResumido("");
        setNomeCompleto("");
        setCpf("");
        setEmail("");
        setLogin("");
        setSenha("");
        setUltimoAcesso(null);

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

        session.setAttribute("novoUsuario", "S");

        return "usuarioman";
    }

    public String editaUsuario() {

        try {

            Usuario usuario = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");

            if(usuario != null){

                usuario = getUsuarioFacade().recuperaUsuario(usuario);

                setIdUsuario(usuario.getId().toString());
                setEmail(usuario.getEmail());
                setSenhaAtual(null);
                setLogin(usuario.getLogin());
                setNomeResumido(usuario.getNomeResumido());
                setNomeCompleto(usuario.getNomeCompleto());
                setCpf(usuario.getCpf());
            }

        } catch (Exception ex) {
            Logger.getLogger(UsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "usuarioman";
    }

    public String emitirRelatorioUsuarioDependente() {

        Usuario usuario = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");

        setIdUsuario(usuario.getId().toString());

        return "emitirrelatoriousuariorel";
    }

    public String incluir() {

        try {

            Usuario usuario = new Usuario(nomeResumido, nomeCompleto, cpf, login, senha, confirmaSenha, email, Calendar.getInstance().getTime());

            try{

                getUsuarioFacade().validaInclusaoAlteracao(usuario);

                getUsuarioFacade().incluiUsuario(usuario);

                setIdUsuario(usuario.getId().toString());

                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

                session.setAttribute("usuarioLogado", usuario);
                session.setAttribute("novoUsuario", null);

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getBundle().getString("usuarioIncluidoSucesso"), getBundle().getString("usuarioIncluidoSucesso")));

            }catch(Exception e){

                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, e);

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));

                return "usuarioman";
            }

        } catch (Exception ex) {

            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, getBundle().getString("usuarioIncluidoFalha"), getBundle().getString("usuarioIncluidoFalha")));

            return "usuarioman";
        }

        return "home";
    }

    public void alterarUsuarioSenhaAtual() {

        try{

            Usuario usuario = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");

            usuario = getUsuarioFacade().recuperaUsuario(usuario);

            usuario.setSenhaAtual(senhaAtual);
            usuario.setSenha(senha);
            usuario.setConfirmaSenha(confirmaSenha);

            getUsuarioFacade().validaSenhaAlteracao(usuario);

            getUsuarioFacade().alteraUsuario(usuario);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Senha do Usuário alterada com sucesso!", "Senha do Usuário alterada com sucesso!"));

        }catch(Exception e){

            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, e);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
        }
    }

    public void alterar() {

        try {

            Usuario usuario = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");

            usuario = getUsuarioFacade().recuperaUsuario(usuario);

            try{

                getUsuarioFacade().validaInclusaoAlteracao(usuario);

                setIdUsuario(usuario.getId().toString());

                usuario.setEmail(email);
                usuario.setNomeResumido(nomeResumido);
                usuario.setNomeCompleto(nomeCompleto);
                usuario.setCpf(cpf);
                usuario.setConfirmaSenha(confirmaSenha);

                getUsuarioFacade().alteraUsuario(usuario);

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getBundle().getString("usuarioAlteradoSucesso"), getBundle().getString("usuarioAlteradoSucesso")));

            }catch(Exception e){

                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, e);

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, getBundle().getString("usuarioAlteradoFalha"), getBundle().getString("usuarioAlteradoFalha")));
            }


        } catch (Exception ex) {

            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, getBundle().getString("usuarioAlteradoFalha"), getBundle().getString("usuarioAlteradoFalha")));
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

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = AndRodHelper.criptografaSenha(confirmaSenha);
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = AndRodHelper.criptografaSenha(senhaAtual);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataAtual() {
        return dataAtual;
    }

    public void setDataAtual(Date dataAtual) {
        this.dataAtual = dataAtual;
    }

    public Date getUltimoAcesso() {
        return ultimoAcesso;
    }

    public void setUltimoAcesso(Date ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }
}