/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.assessmentsystem.assessmentsystem.controller;

import br.com.assessmentsystem.assessmentsystem.dao.JdbcUserDao;
import br.com.assessmentsystem.assessmentsystem.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *HttpSession java get object
 * @author marcelo
 */
@Controller
public class LoginController {
    
    private JdbcUserDao userDao = new JdbcUserDao();
    
    @RequestMapping(Routes.loginShow)
    public String loginForm() {
        return Routes.loginShow;
    }
    
    @RequestMapping(Routes.loginAct)
    public String loginAct(User user, HttpSession session){
    	if (userDao.existUser(user)) {
    		session.setAttribute("user", user);
    		//javax.swing.JOptionPane.showMessageDialog(null, "Teste:"+user.getId());
    	            
    		return "redirect:"+ Routes.main;
    	}
        else {
            /*javax.swing.JOptionPane.showMessageDialog(null, "Login ou Senha estão inválidos, por favor"
                    + "tente novamente");
        */
        return "redirect:"+Routes.loginShow;
        }
    }
  
    @RequestMapping(Routes.logout)
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:"+Routes.program;
    }
    
    @RequestMapping(Routes.loginNew)
    public String loginNewForm() {
        return Routes.loginNew;
    }
    
    @RequestMapping(Routes.loginNewAct)
    public String loginNewAct(User user){
        if (userDao.existUser(user)){ 
            //javax.swing.JOptionPane.showMessageDialog(null, "Login ou senha já existem, favor cadastrar outro");
            return "redirect:"+Routes.loginNew;
        }
        else {
            userDao.createUser(user);
            //javax.swing.JOptionPane.showMessageDialog(null, "Usuário criado com sucesso");
            return "redirect:"+Routes.loginShow; 
        }
    }
}
