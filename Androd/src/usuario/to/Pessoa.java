package usuario.to;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author André V. M. D. Bandera
 * @author Rodrigo G. M. Catto
 */
@Entity
@Table(name = "PESSOA")
@DiscriminatorValue("Pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="TIPOPESSOA", discriminatorType=DiscriminatorType.STRING, length=30)
public abstract class Pessoa implements Serializable {

    @Id
    @SequenceGenerator(name="PESSOA_SEQUENCE_GENERATOR", sequenceName="PESSOA_ID_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PESSOA_SEQUENCE_GENERATOR")
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nomeResumido;

    @Column(nullable = false, length = 255)
    private String nomeCompleto;
    
    @Column(length = 14)
    private String cpf;

    public Pessoa() {
    }

    public Pessoa(Long id) {
        this.id = id;
    }

    public Pessoa(String nomeResumido, String nomeCompleto, String cpf) {
        this.nomeResumido   = nomeResumido;
        this.nomeCompleto   = nomeCompleto;
        this.cpf            = cpf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}