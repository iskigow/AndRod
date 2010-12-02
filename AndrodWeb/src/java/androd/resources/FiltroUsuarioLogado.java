/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package androd.resources;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author andrevmdb
 */
public class FiltroUsuarioLogado implements Filter {

    public FiltroUsuarioLogado() {
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest      = (HttpServletRequest) request;
        HttpServletResponse httpResponse    = (HttpServletResponse) response;

        if(httpRequest.getSession().getAttribute("usuarioLogado") == null && 
                !httpRequest.getRequestURL().toString().contains("faces/usuariologin.xhtml") &&
                !httpRequest.getRequestURL().toString().contains("AndrodWeb/imagens/externo/") &&
                !httpRequest.getRequestURL().toString().contains("AndrodWeb/css/") &&
                !httpRequest.getRequestURL().toString().contains("AndrodWeb/js/")){

            if((httpRequest.getSession().getAttribute("novoUsuario") == null)
                    || (httpRequest.getSession().getAttribute("novoUsuario") != null
                            && httpRequest.getSession().getAttribute("novoUsuario").toString().equals("S")
                            && !httpRequest.getRequestURL().toString().contains("faces/usuarioman.xhtml"))){

                httpResponse.sendRedirect(httpRequest.getContextPath() + "/faces/usuariologin.xhtml");

                return;
            }
        }

        chain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void destroy() {
    }
}