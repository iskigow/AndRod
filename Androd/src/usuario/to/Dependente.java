/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario.to;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author André V. M. D. Bandera
 * @author Rodrigo G. M. Catto
 */
@Entity
@Table(name = "DEPENDENTE")

@DiscriminatorValue("Dependente")

@NamedQueries({
   @NamedQuery(name = "Dependente.findByLoginUsuario", query = "SELECT dependente FROM Dependente dependente WHERE dependente.usuarioSistema.login = :login")
})
public class Dependente extends Pessoa implements Serializable {

    @ManyToOne(targetEntity = Usuario.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "idusuario", nullable = false)
    private Usuario usuarioSistema;

    public Dependente() {
    }

    public Dependente(Long idDependente) {
        setId(idDependente);
    }
    
    public Dependente(String nomeResumido, String nomeCompleto, String cpf, Usuario usuario) {
        super(nomeResumido, nomeCompleto, cpf);
        setUsuarioSistema(usuario);
    }

    public Dependente(Long idDependente, String nomeResumido, String nomeCompleto, String cpf, Usuario usuario) {
        super(nomeResumido, nomeCompleto, cpf);
        setId(idDependente);
        setUsuarioSistema(usuario);
    }

    public Usuario getUsuarioSistema() {
        return usuarioSistema;
    }

    public void setUsuarioSistema(Usuario usuarioSistema) {
        this.usuarioSistema = usuarioSistema;
    }
}