package androd.helper;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author andrevmdb
 */
public class AndRodHelper {

    public static Boolean ehNuloBranco(String parametroString) {

        if(parametroString == null || parametroString.length() == 0){
            return Boolean.TRUE;
        }
        
        return Boolean.FALSE;
    }

    /**
     * Método para criar hash da senha informada
     * 
     * @return
     */
    public static String criptografaSenha(String senha) {

        if(!ehNuloBranco(senha)){

            try {

                MessageDigest md        = MessageDigest.getInstance("MD5");

                BigInteger hash         = new BigInteger(1, md.digest(senha.getBytes("UTF-8")));

                String senhaCriptografa = hash.toString(16);

                return senhaCriptografa;

            } catch (UnsupportedEncodingException ex) {

                Logger.getLogger(AndRodHelper.class.getName()).log(Level.SEVERE, null, ex);

            } catch (NoSuchAlgorithmException e) {

                Logger.getLogger(AndRodHelper.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        return senha;
    }

    private static boolean matches(String regExp, String str) {
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static boolean isInteiro(String str) {
        return matches("[0-9]+", str);
    }

    public static boolean isFlutuanteBR(String str) {
        return matches("[0-9]*[,][0-9]+", str);
    }

    public static boolean isFlutuanteUS(String str) {
        return matches("[0-9]*[.][0-9]+", str);
    }

    public static boolean isMoedaBR (String str) {
        return matches("[0-9]{1,3}([.][0-9]{3})*([,][0-9]+)?", str);
    }

    public static boolean isMoedaUS (String str) {
        return matches("[0-9]{1,3}([,][0-9]{3})*([.][0-9]+)?", str);
    }

    public static boolean isNumber(String str) {
        return (isInteiro(str) || isFlutuanteBR(str) || isFlutuanteUS(str) || isMoedaBR(str) || isMoedaUS(str));
    }
}