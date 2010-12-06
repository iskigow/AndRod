package financa.to;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import usuario.to.Usuario;

/**
 * @author André V. M. D. Bandera
 * @author Rodrigo G. M. Catto
 */
@Entity
@Table(name="FINANCA")

@NamedQueries({
   @NamedQuery(name = "Financa.findAll", query = "SELECT f FROM Financa f WHERE f.usuario = :usuario ORDER BY f.dataFinanca ASC"),
   @NamedQuery(name = "Financa.findById", query = "SELECT f FROM Financa f WHERE f.id = :id AND f.usuario = :usuario")
//   @NamedQuery(name = "Financa.findSuperiorById", query = "SELECT cf.superior FROM ClassificacaoFinanca cf WHERE cf.id = :id AND cf.usuario = :usuario"),
//   @NamedQuery(name = "Financa.findAllUltimoNivel", query = "SELECT cf FROM ClassificacaoFinanca cf WHERE cf.usuario = :usuario AND NOT EXISTS ( SELECT cfAux FROM ClassificacaoFinanca cfAux WHERE cfAux.superior = cf AND cfAux.usuario = cf.usuario )"),
//   @NamedQuery(name = "Financa.findChild", query = "SELECT cf FROM ClassificacaoFinanca cf WHERE cf.superior = :superior AND cf.usuario = :usuario")
})
public class Financa implements Serializable {

    @Id
    @SequenceGenerator(name="FINANCA_SEQUENCE_GENERATOR", sequenceName="FINANCA_ID_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FINANCA_SEQUENCE_GENERATOR")
    @Column(nullable = false, unique = true)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private TipoFinanca tipoFinanca;

    @Column(nullable=false)
    private BigDecimal valor;

    @Temporal(TemporalType.DATE)
    @Column(nullable=false)
    private Date dataFinanca;

    @Temporal(TemporalType.DATE)
    @Column(nullable=false)
    private Date dataCadastro;

    @Column(nullable=false)
    private String descricao;

    @ManyToOne(targetEntity=ClassificacaoFinanca.class, fetch=FetchType.LAZY)
    @JoinColumn(name = "idclassificacaofinanca")
    private ClassificacaoFinanca classificacaoFinanca;

    @ManyToOne(targetEntity = Usuario.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "idusuario", nullable = false)
    private Usuario usuario;

    /**
     * Construtor padrão - seta a data de cadastro da financa
     */
    public Financa() {
        this.dataCadastro = Calendar.getInstance().getTime();
    }

    /**
     * Costrutor da financa que seta um Id e um Usuário
     *
     * @param id
     * @param usuario
     */
    public Financa(Long id,Usuario usuario){
        this();
        this.id=id;
        this.usuario=usuario;
    }

    /**
     * Construtor usado para criar uma Financa com Id (finalidade de fazer busca)
     *
     * @param id
     */
    public Financa(Long id){
        this(id,null);
    }

    /**
     * Cadastro de uma nova financa pelo Usuario
     * @param usuario
     */
    public Financa(Usuario usuario){
        this(null,usuario);
    }

    public ClassificacaoFinanca getClassificacaoFinanca() {
        return classificacaoFinanca;
    }

    public void setClassificacaoFinanca(ClassificacaoFinanca classificacaoFinanca) {
        this.classificacaoFinanca = classificacaoFinanca;
    }

    public Date getDataFinanca() {
        return dataFinanca;
    }

    public void setDataFinanca(Date dataFinanca) {
        this.dataFinanca = dataFinanca;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoFinanca getTipoFinanca() {
        return tipoFinanca;
    }

    public void setTipoFinanca(TipoFinanca tipoFinanca) {
        this.tipoFinanca = tipoFinanca;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}