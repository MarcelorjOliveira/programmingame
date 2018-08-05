/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.assessmentsystem.assessmentsystem.interceptor;

import br.com.assessmentsystem.assessmentsystem.controller.Routes;
import br.com.assessmentsystem.assessmentsystem.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author marcelooliveira
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller) throws Exception {
        String uri = request.getRequestURI();
        if(uri.endsWith(Routes.loginAct) || uri.endsWith(Routes.loginNew) || uri.endsWith(Routes.login) || uri.endsWith(Routes.loginNewAct) || uri.endsWith(Routes.loginShow) || uri.contains("resources")) {
        	return true;
        }
        
        if(request.getSession().getAttribute("user") != null){
        	
            return true;
        }
        
        response.sendRedirect(Routes.loginShow);
        return false;
    }
}
