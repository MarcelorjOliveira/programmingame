/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.assessmentsystem.assessmentsystem.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.assessmentsystem.assessmentsystem.dao.ExerciseDao;
import br.com.assessmentsystem.assessmentsystem.dao.MovementDao;
import br.com.assessmentsystem.assessmentsystem.model.Exercise;
import br.com.assessmentsystem.assessmentsystem.model.ExerciseChooser;
import br.com.assessmentsystem.assessmentsystem.model.Movement;
import br.com.assessmentsystem.assessmentsystem.model.OOJavaExercise;
import br.com.assessmentsystem.assessmentsystem.model.Solution;
import br.com.assessmentsystem.assessmentsystem.model.User;

/**
 *
 * @author marcelo
 */

@Controller
public class ExercisesController {

	private String resolution;

	private ExerciseChooser chooser;
	private Exercise exercise;

	@RequestMapping(Routes.exercises)
	public String exercise() {
		return "redirect:" + Routes.exercisesNew;
	}

	@RequestMapping(Routes.exercisesCreateDirectory)
	public void createDirectory(HttpServletRequest request) {
		
		//too many connections 
		
		String path = request.getParameter("directory");
		
		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");
		
		String userId = Integer.toString(user.getId());

		String exerciseId = (String) session.getAttribute("exerciseId");

		File fileDirectory = new File("/usr/local/tomcat/students/"+userId+"/"+exerciseId+"/"+path);
		
		if (!path.contains("..")) {
			fileDirectory.mkdirs();
			Exercise.writeFile("", fileDirectory);
		}
		
		if(path.contains("..") ) {
			System.out.println("Não é");
		}
		
		//criar
		
		// não deixar criar se tiver .. ou caracteres inválidos
		
		//File fileDirectory = new File(initialDirectory + Integer.toString(this.userId));

		//fileDirectory.mkdir();

		//fileDirectory = new File(initialDirectory + root);

		//fileDirectory.mkdir();
	}
	
	@RequestMapping(Routes.exercisesCreateFile)
	public String createFile(String name) {
		return "";
	}

	@RequestMapping(Routes.exercisesUpdateDirectoryTree)
	public void updateDirectoryTree() {
		
	}
	
	@RequestMapping(Routes.exercisesNew)
	public String newExercise(HttpServletRequest request, Model model) {
		String titulo = "";
		
		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");

		ExerciseDao exerciseDao = new ExerciseDao();
		
		int id = Integer.parseInt(request.getParameter("exerciseId"));
		
		Exercise exercise = exerciseDao.findById(id); 
		
		//System.out.println("ReqId"+request.getParameter("exerciseId"));
		
		//System.out.println("Title:"+exercise.getStatement());
		//System.out.println("Id:"+exercise.getId());
		
		//model.addAttribute("title", exercise.getStatement());
		//model.addAttribute("exerciseIdController", exercise.getId());
		
		session.setAttribute("title", exercise.getStatement());
		session.setAttribute("exerciseId", Integer.toString(exercise.getId()) );
		session.setAttribute("exerciseName", exercise.getName());
				
		/*
		 * Class classe = User.class; for (Field atributo : classe.getDeclaredFields())
		 * { titulo += atributo.getName()+"<br>"; }
		 */

		return Routes.exercisesNew;
	}

	@RequestMapping(Routes.exercisesUpdate)
	public String updateExercise(HttpServletRequest request, Model model) {
		model.addAttribute("title", exercise.getStatement());

		model.addAttribute("resolutionParam", resolution);
		return Routes.exercisesNew;
	}

	@RequestMapping(Routes.exercisesAct)
	public String runExercise(HttpServletRequest request, Model model) {
		Movement movement = new Movement();
		MovementDao movementDao = new MovementDao();
		
		User user = (User) request.getSession().getAttribute("user");

		resolution = request.getParameter("resolution");
		
		// javax.swing.JOptionPane.showMessageDialog(null, resolution);
		
		int id = Integer.parseInt(request.getParameter("exerciseId"));
		
		Exercise exercise = new ExerciseDao().findById(id);
		
		//System.out.println("Exercicio:"+Integer.toString(user.getId()) );
		
		exercise.setCode(resolution);
		exercise.setUserId(user.getId());
		//exercise.createDirectory();
		
		exercise.buildGrading(resolution);

		movement.setExerciseId(exercise.getId());
		movement.setUserId(user.getId());
		movement.setCodeUsed(exercise.getCode());
		movement.setMark(exercise.getTestMark());

		movementDao.insert(movement);

		JSONArray solutionsJSON = new JSONArray();
		JSONObject jsonObject = new JSONObject();

		// javax.swing.JOptionPane.showMessageDialog(null, exercise.getTestMark());
		List<Solution> solutions = exercise.getSolutions();

		model.addAttribute("mark", exercise.getTestMark());

		for (int i = 0; i < solutions.size(); i++) {
			System.out.println(solutions.get(i).getDescription());
			solutionsJSON.put(solutions.get(i).getDescription());
		}

		jsonObject.put("descriptions", solutionsJSON);

		System.out.println(jsonObject.toString());

		model.addAttribute("corrections", jsonObject.toString());
		model.addAttribute("exerciseId",request.getParameter("exerciseId"));
		
		
		if (exercise.hasCompileErrors != true) {
			// exercicio.salvarBancoDeDados(codigoUsuario, conexao);
			//if (chooser.canDoNextExercise() == true) {
				return "redirect:" + Routes.exercisesNew;
			//} else {
				// javax.swing.JOptionPane.showMessageDialog(null, "Parabéns. Você passou no
				// teste!");
				//return "redirect:" + Routes.main;
			//}
		} else {
			// exercicio.salvarBancoErroDeCompilacao(codigoUsuario, conexao);
			if (exercise.endOfAttempts == true) {
				if (chooser.canDoNextExercise() == true) {
					/*
					 * javax.swing.JOptionPane.showMessageDialog(null, "Estouro de " +
					 * "quantidade de tentativas atingido. " +
					 * "Por favor, fazer o próximo exercício");
					 */
					return "redirect:" + Routes.exercisesNew;

				} else {
					// javax.swing.JOptionPane.showMessageDialog(null, "Você foi reprovado no
					// teste");
					return "redirect:" + Routes.main;
				}
			} else {
				return "redirect:" + Routes.exercisesUpdate;
			}
		}
	}

}
