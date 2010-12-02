package financa.to;

import androd.helper.AndRodHelper;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import usuario.to.Usuario;

/**
 * @author André V. M. D. Bandera
 * @author Rodrigo G. M. Catto
 */
@Entity
@Table(name="CLASSIFICACAO")

@NamedQueries({
   @NamedQuery(name = "ClassificacaoFinanca.findById", query = "SELECT cf FROM ClassificacaoFinanca cf WHERE cf.id = :id AND cf.usuario = :usuario"),
   @NamedQuery(name = "ClassificacaoFinanca.findSuperiorById", query = "SELECT cf.superior FROM ClassificacaoFinanca cf WHERE cf.id = :id AND cf.usuario = :usuario"),
   @NamedQuery(name = "ClassificacaoFinanca.findAll", query = "SELECT cf FROM ClassificacaoFinanca cf WHERE cf.usuario = :usuario"),
   @NamedQuery(name = "ClassificacaoFinanca.findAllUltimoNivel", query = "SELECT cf FROM ClassificacaoFinanca cf WHERE cf.usuario = :usuario AND NOT EXISTS ( SELECT cfAux FROM ClassificacaoFinanca cfAux WHERE cfAux.superior = cf AND cfAux.usuario = cf.usuario )"),
   @NamedQuery(name = "ClassificacaoFinanca.findChild", query = "SELECT cf FROM ClassificacaoFinanca cf WHERE cf.superior = :superior AND cf.usuario = :usuario")
})
public class ClassificacaoFinanca implements Serializable {

    @Id
    @Column(nullable = false, unique = true)
    private String id;

    @ManyToOne(fetch=FetchType.LAZY,targetEntity=Usuario.class)
    @JoinColumn(nullable=false)
    private Usuario usuario;

    @Column(nullable = false, unique = true)
    private String descricao;

    @Transient
    private String descricaoResumida;

    @ManyToOne(fetch=FetchType.LAZY,targetEntity=ClassificacaoFinanca.class,cascade=CascadeType.REFRESH)
    private ClassificacaoFinanca superior;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dataCadastro;

    public ClassificacaoFinanca() {
        this.dataCadastro = new Date();
    }

    /**
     * Construtor responsavel por montar uma nova classificação.
     * 
     * @param descricao
     * @param numeroIdentificacao
     * @param classificacaoSuperior
     */
    public ClassificacaoFinanca(String descricao, String numeroIdentificacao, ClassificacaoFinanca classificacaoSuperior, Usuario usuario) {
        this();
        this.setDescricao(descricao);
        this.setSuperior(classificacaoSuperior);
        this.setUsuario(usuario);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricaoResumida() {
        StringBuilder builder = new StringBuilder();
        if (descricao != null && !descricao.isEmpty()) {
            for (String palavra : descricao.split(" ")) {
                if (palavra != null && palavra.length() > 3) {
                    builder.append(" ").append(palavra.substring(0, 3));
                } else if (AndRodHelper.isNumber(palavra)) {
                    builder.append(" ").append(palavra);
                }
            }
            if (builder.length() > 0)
                builder.deleteCharAt(0);
        }
        descricaoResumida=builder.toString().toUpperCase();
        return descricaoResumida;
    }

    public ClassificacaoFinanca getSuperior() {
        return superior;
    }

    public void setSuperior(ClassificacaoFinanca superior) {
        this.superior = superior;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String superiorInfo() {
        StringBuilder saida = new StringBuilder();
        StringBuilder nivel = new StringBuilder("\n  ");
        ClassificacaoFinanca cf = this;
        Deque<ClassificacaoFinanca> pilha = new ArrayDeque();
        while (cf != null) {
            pilha.push(cf);
            cf = cf.getSuperior();
        }
        cf = pilha.pop();
        saida.append(cf.toString());
        while (!pilha.isEmpty()) {
            cf = pilha.pop();
            saida.append(nivel).append(cf.toString());
            nivel.append("  ");
        }
        return saida.toString();
    }

    @Override
    public String toString() {
        return new StringBuilder("").append(this.id).append(" - ").append(this.getDescricaoResumida()).toString();
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}