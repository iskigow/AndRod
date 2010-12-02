package financa.to;

/**
 * @author André V. M. D. Bandera
 * @author Rodrigo G. M. Catto
 */
public enum TipoFinanca {
 
    CREDITO(1),

    DEBITO(-1);

    private Integer valor;

    private TipoFinanca(Integer valor) {
        this.valor = valor;
    }

    public Integer getValor() {
        return valor;
    }

    public String getNome() {
        return this.name();
    }
}