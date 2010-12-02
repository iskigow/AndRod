package usuario.to;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author André V. M. D. Bandera
 * @author Rodrigo G. M. Catto
 */
@Entity
@Table(name = "USUARIO")

@DiscriminatorValue("Usuário")

@NamedQueries({
   @NamedQuery(name = "Usuario.findByLoginSenha", query = "SELECT usuario FROM Usuario usuario WHERE usuario.login = :login and usuario.senha = :senha"),
   @NamedQuery(name = "Usuario.findByLogin", query = "SELECT usuario FROM Usuario usuario WHERE usuario.login = :login")
})
public class Usuario extends Pessoa {

    @Column(nullable = false, length = 20, unique = true)
    private String login;

    @Column(nullable = false, length = 40)
    private String senha;

    @Column(nullable = false, length = 255)
    private String email;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataUltimoAcesso;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuarioSistema")
    private List<Dependente> dependentes;
    
//
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuario")
//    private List<Financa> financas;

    private transient String confirmaSenha;

    private transient String senhaAtual;

    public Usuario() {
    }

    public Usuario(Long idUsuario) {
        super(idUsuario);
    }

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario(String nomeResumido, String nomeCompleto, String cpf, String login, String senha) {

        super(nomeResumido, nomeCompleto, cpf);

        this.login = login;
        this.senha = senha;
    }

    public Usuario(String nomeResumido, String nomeCompleto, String cpf, String login, String senha, String confirmaSenha, String email) {

        this(nomeResumido, nomeCompleto, cpf, login, senha);

        this.email          = email;
        this.confirmaSenha  = confirmaSenha;
    }

    public Usuario(String nomeResumido, String nomeCompleto, String cpf, String login, String senha, String confirmaSenha, String email, Date ultimoAcesso) {
        this(nomeResumido, nomeCompleto, cpf, login, senha, confirmaSenha, email);

        dataUltimoAcesso = ultimoAcesso;
    }

    public Date getDataUltimoAcesso() {
        return dataUltimoAcesso;
    }

    public void setDataUltimoAcesso(Date dataUltimoAcesso) {
        this.dataUltimoAcesso = dataUltimoAcesso;
    }

//    public List<Financa> getFinancas() {
//        return financas;
//    }
//
//    public void setFinancas(List<Financa> financas) {
//        this.financas = financas;
//    }

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
        this.senha = senha;
    }

    public List<Dependente> getDependentes() {
        return dependentes;
    }

    public void setDependentes(List<Dependente> dependentes) {
        this.dependentes = dependentes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }
}