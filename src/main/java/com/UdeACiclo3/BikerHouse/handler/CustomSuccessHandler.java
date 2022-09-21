package com.UdeACiclo3.BikerHouse.handler;

import ch.qos.logback.core.joran.action.IADataForComplexProperty;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component ///Se debe verificar al iniciar el proceso
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();

    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws IOException{
            String targetUrl=determineTargetUrl(authentication); // Llama al metodo que me da la url donde debo ir
            if ((response.isCommitted())){
                System.out.println("No se puede redireccionar, error CustomSuccesHandler");
                return;
            }
            redirectStrategy.sendRedirect(request,response, targetUrl);
    }

    protected String determineTargetUrl(Authentication authentication){
        String url="";
        Collection<? extends GrantedAuthority> authorities =authentication.getAuthorities(); // trae informacion de las personas logueadas y sus permisos
        List<String> roles=new ArrayList<String>();
        for (GrantedAuthority a: authorities){
            roles.add(a.getAuthority());
        }
        if (esAdministrativo(roles)){
            url="/VerEmpresas";
        } else if (esOperativo(roles)) {
            url="/VerMovimientos";
        } else {
            url="/Denegado";
        }

        return url;
    }

    private boolean esOperativo(List<String> roles){
        if(roles.contains("ROLE_USER")){
            return true;
        }
        return false;
    }

    private boolean esAdministrativo(List<String> roles){
        if(roles.contains("ROLE_ADMIN")){
            return true;
        }
        return false;
    }

}
