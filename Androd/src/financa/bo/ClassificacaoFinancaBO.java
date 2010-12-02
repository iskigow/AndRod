/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package financa.bo;

import financa.dao.ClassificacaoFinancaDAO;

/**
 *
 * @author Rodrigo G. M. Catto
 */
public class ClassificacaoFinancaBO {

    private ClassificacaoFinancaDAO cfdao;

    public ClassificacaoFinancaDAO getCfdao() {
        if (cfdao == null) {
            cfdao = new ClassificacaoFinancaDAO();
        }
        return cfdao;
    }

    public ClassificacaoFinancaBO() {
    }

}
