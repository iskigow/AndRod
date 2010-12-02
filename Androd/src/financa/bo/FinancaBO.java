/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package financa.bo;

import financa.dao.FinancaDAO;

/**
 *
 * @author Rodrigo G. M. Catto
 */
public class FinancaBO {

    private FinancaDAO fdao;

    public FinancaDAO getFdao() {
        if (fdao == null) {
            fdao = new FinancaDAO();
        }
        return fdao;
    }

    public FinancaBO() {
    }

}
