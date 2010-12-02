package usuario.bo;

import androd.bo.AndRodBO;
import usuario.dao.DependenteDAO;
import androd.helper.AndRodHelper;
import usuario.to.Dependente;

public class DependenteBO implements AndRodBO{

    private DependenteDAO dependenteDAO;

    public DependenteDAO getDependenteDAO() {

        if(dependenteDAO == null){
            dependenteDAO = new DependenteDAO();
        }

        return dependenteDAO;
    }

    public DependenteBO() {
    }

    public void validaObrigatorios(Object instanciaDependente) throws Exception {

        if(instanciaDependente == null){
            throw new NullPointerException("Parâmetro Dependente é obrigatório.");
        }

        Dependente dependente = (Dependente) instanciaDependente;

        AndRodHelper.ehNuloBranco(dependente.getNomeResumido());
        AndRodHelper.ehNuloBranco(dependente.getNomeCompleto());
    }

}