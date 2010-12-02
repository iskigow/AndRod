/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package androd.helper;

import usuario.ctr.mb.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class EmailValidator implements Validator {

    public void validate(FacesContext facesContext, UIComponent uIComponent, Object object) throws ValidatorException {

        String enteredEmail = (String) object;

        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(enteredEmail);

        boolean matchFound = m.matches();

        if (!matchFound) {

            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email informado é inválido.", "Email informado é inválido.");
            
            throw new ValidatorException(mensagem);
        }
    }
}