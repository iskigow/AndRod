package usuario.bo;

import androd.bo.AndRodBO;
import usuario.dao.UsuarioDAO;
import androd.helper.AndRodHelper;
import usuario.to.Usuario;

public class UsuarioBO implements AndRodBO{

    private UsuarioDAO usuarioDAO;

    public UsuarioBO(){
    }

    public UsuarioDAO getUsuarioDAO() {

        if(usuarioDAO == null){
            usuarioDAO = new UsuarioDAO();
        }

        return usuarioDAO;
    }

    public void validaInclusaoAlteracao(Usuario usuario) throws Exception{

        /** Inclusao **/
        if(usuario.getId() == null){

            Usuario usuarioLogin = getUsuarioDAO().recuperaUsuarioPorLogin(usuario);

            if(usuarioLogin != null){
                throw new Exception("Login já utilizado.");
            }

            validaSenhaInclusao(usuario);
        }
    }

    public void validaObrigatorios(Object instanciaUsuario) throws Exception{

        if(instanciaUsuario == null){
            throw new NullPointerException("Usuário pré - existente é obrigatório.");
        }

        Usuario usuario = (Usuario) instanciaUsuario;

        AndRodHelper.ehNuloBranco(usuario.getNomeResumido());
        AndRodHelper.ehNuloBranco(usuario.getNomeCompleto());
    }

    public void validaSenhaAlteracao(Usuario usuario) throws Exception {

        /** Alteracao **/
        if(usuario.getId() != null){

            if(usuario.getSenhaAtual() == null || usuario.getSenhaAtual().length() <= 0){
                throw new Exception("Senha Atual é obrigatória.");
            }

            if(usuario.getSenha() == null || usuario.getSenha().length() == 0){
                throw new Exception("Nova Senha é obrigatória.");
            }

            if(usuario.getConfirmaSenha() == null || usuario.getConfirmaSenha().length() == 0){
                throw new Exception("Repetir Nova Senha é obrigatória.");
            }

            Usuario usuarioSenhaAtual = new Usuario(usuario.getLogin(), usuario.getSenhaAtual());

            Usuario usuarioSenha = getUsuarioDAO().recuperaUsuarioPorLoginESenha(usuarioSenhaAtual);

            if(usuarioSenha == null){
                throw new Exception("A Senha Atual informa está inválida!");
            }

            if(!usuario.getSenha().equals(usuario.getConfirmaSenha())){
                throw new Exception("Nova Senha não confere com Repetir Nova Senha.");
            }
        }
    }

    private void validaSenhaInclusao(Usuario usuario) throws Exception {

        if(usuario.getSenha() == null || usuario.getSenha().length() == 0){
            throw new Exception("Senha é obrigatória.");
        }

        if(usuario.getConfirmaSenha() == null || usuario.getConfirmaSenha().length() == 0){
            throw new Exception("Repetir Senha é obrigatória.");
        }

        if(!usuario.getSenha().equals(usuario.getConfirmaSenha())){
            throw new Exception("Repetir Senha não confere com Senha.");
        }
    }
}