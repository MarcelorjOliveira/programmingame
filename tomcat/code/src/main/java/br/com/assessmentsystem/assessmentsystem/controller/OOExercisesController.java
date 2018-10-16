/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.assessmentsystem.assessmentsystem.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
public class OOExercisesController {

	private String resolution;

	private ExerciseChooser chooser;
	private Exercise exercise;

	@RequestMapping(Routes.OOExercises)
	public String exercise() {
		chooser = new ExerciseChooser();
		return "redirect:" + Routes.OOExercisesNew;
	}

	@RequestMapping(Routes.OOExercisesNew)
	public String newExercise(HttpServletRequest request, Model model) {
		String titulo = "";
		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");

		//exercise = chooser.chooseExercise(user.getId());
		
		//ExerciseChooserDao ooExercises = new ExerciseChooserDao(); 
		
		model.addAttribute("title", "Escreva uma classe Soma que some dois números");
		/*
		 * Class classe = User.class; for (Field atributo : classe.getDeclaredFields())
		 * { titulo += atributo.getName()+"<br>"; }
		 */

		return Routes.OOExercisesNew;
	}

	@RequestMapping(Routes.OOExercisesUpdate)
	public String updateExercise(HttpServletRequest request, Model model) {
		model.addAttribute("title", exercise.getStatement());

		model.addAttribute("resolutionParam", resolution);
		return Routes.basicExercisesNew;
	}

	@RequestMapping(Routes.OOExercisesAct)
	public String runExercise(HttpServletRequest request, Model model) {
		Movement movement = new Movement();
		MovementDao movementDao = new MovementDao();
		
		User user = (User) request.getSession().getAttribute("user");

		resolution = request.getParameter("resolution");
		
		// javax.swing.JOptionPane.showMessageDialog(null, resolution);
		
		Exercise exercise = new OOJavaExercise();
		
		exercise.setUserId(user.getId());
		exercise.setId(9);
		exercise.setName("SumExercise");
		exercise.setCode(resolution);
		
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
		
		if (exercise.hasCompileErrors != true) {
			// exercicio.salvarBancoDeDados(codigoUsuario, conexao);
			if (chooser.canDoNextExercise() == true) {
				return "redirect:" + Routes.OOExercisesNew;
			} else {
				// javax.swing.JOptionPane.showMessageDialog(null, "Parabéns. Você passou no
				// teste!");
				return "redirect:" + Routes.main;
			}
		} else {
			// exercicio.salvarBancoErroDeCompilacao(codigoUsuario, conexao);
			if (exercise.endOfAttempts == true) {
				if (chooser.canDoNextExercise() == true) {
					/*
					 * javax.swing.JOptionPane.showMessageDialog(null, "Estouro de " +
					 * "quantidade de tentativas atingido. " +
					 * "Por favor, fazer o próximo exercício");
					 */
					return "redirect:" + Routes.OOExercisesNew;

				} else {
					// javax.swing.JOptionPane.showMessageDialog(null, "Você foi reprovado no
					// teste");
					return "redirect:" + Routes.main;
				}
			} else {
				return "redirect:" + Routes.OOExercisesUpdate;
			}
		}
	}

}
