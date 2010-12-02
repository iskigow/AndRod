package androd.dao;

import java.util.List;

public interface AndrodDAO<T> {

    /**
     *
     * Método responsável por recuperar uma coleção de objetos
     *
     * @return
     * @throws Exception
     */
    List<T> recuperaObjetos() throws Exception;

    /**
     *
     * Método responsável por recuperar um objeto
     *
     * @return
     * @throws Exception
     */
    T recuperaObjeto(T objetoParametro) throws Exception;

    /**
     *
     * Método responsável por incluir um novo objeto
     *
     * @param entidadeInstancia
     * @throws Exception
     */
    void inclui(T entidadeInstancia) throws Exception;

    /**
     *
     * Método responsável por alterar um objeto
     *
     * @param entidadeInstancia
     * @throws Exception
     */
    void altera(T entidadeInstancia) throws Exception;

    /**
     *
     * Método responsável por excluir um objeto
     *
     * @param entidadeInstancia
     * @throws Exception
     */
    void exclui(T entidadeInstancia) throws Exception;
}