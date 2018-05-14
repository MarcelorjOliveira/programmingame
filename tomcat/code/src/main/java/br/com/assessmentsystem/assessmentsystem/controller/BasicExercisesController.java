/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.assessmentsystem.assessmentsystem.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.assessmentsystem.assessmentsystem.dao.MovementDao;
import br.com.assessmentsystem.assessmentsystem.model.Exercise;
import br.com.assessmentsystem.assessmentsystem.model.ExerciseChooser;
import br.com.assessmentsystem.assessmentsystem.model.Movement;
import br.com.assessmentsystem.assessmentsystem.model.User;

/**
 *
 * @author marcelo
 */

@Controller
public class BasicExercisesController {
    
    private String resolution;
    
    private ExerciseChooser chooser;
    private Exercise exercise;
    
    @RequestMapping(Routes.basicExercises)
    public String exercise() {
        chooser = new ExerciseChooser();
        return "redirect:"+Routes.basicExercisesNew;
   }
    
    @RequestMapping(Routes.basicExercisesNew)
    public String newExercise(HttpServletRequest request, Model model){
    	HttpSession session = request.getSession();
    	
    	User user = (User)session.getAttribute("user");
    	
    	exercise = chooser.chooseExercise(user.getId());
    	
    	model.addAttribute("title", exercise.getStatement() );
        return Routes.basicExercisesNew;
    }
    
    @RequestMapping(Routes.basicExercisesUpdate)
    public String updateExercise(HttpServletRequest request, Model model){
        model.addAttribute("title", exercise.getStatement() );
        model.addAttribute("resolutionParam", resolution);
        return Routes.basicExercisesNew;
    }
    
    @RequestMapping(Routes.basicExercisesAct)
    public String runExercise(HttpServletRequest request, Model model){
    	Movement movement = new Movement();
    	MovementDao movementDao = new MovementDao();
    	User user = (User)request.getSession().getAttribute("user");
    	
    	resolution = request.getParameter("resolution");
        //javax.swing.JOptionPane.showMessageDialog(null, resolution);
        exercise.buildGrading(resolution);   
         
        movement.setExerciseId(exercise.getId());
        movement.setUserId(user.getId());
        movement.setCodeUsed(exercise.getCode());
        movement.setMark(exercise.getTestMark());
        
        movementDao.insert(movement);
        
        //javax.swing.JOptionPane.showMessageDialog(null, exercise.getTestMark());
        
        model.addAttribute("mark", exercise.getTestMark());
        
        if (exercise.hasCompileErrors != true) {
            //exercicio.salvarBancoDeDados(codigoUsuario, conexao);
            if (chooser.canDoNextExercise() == true) {
                return "redirect:"+Routes.basicExercisesNew;
            } else {
                //javax.swing.JOptionPane.showMessageDialog(null, "Parabéns. Você passou no teste!");
                return "redirect:"+Routes.main;
            }
        } else {
            //exercicio.salvarBancoErroDeCompilacao(codigoUsuario, conexao);
            if (exercise.endOfAttempts == true) {
                if (chooser.canDoNextExercise() == true) {
                   /* javax.swing.JOptionPane.showMessageDialog(null, "Estouro de "
                            + "quantidade de tentativas atingido. "
                            + "Por favor, fazer o próximo exercício");
                    */
                	return "redirect:"+Routes.basicExercisesNew;
                } else {
                    //javax.swing.JOptionPane.showMessageDialog(null, "Você foi reprovado no teste");
                    return "redirect:"+Routes.main;
                }
            }
            else {
                return "redirect:"+Routes.basicExercisesUpdate;
            }
        }
    }
    
}
