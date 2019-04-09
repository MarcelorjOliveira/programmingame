/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.assessmentsystem.assessmentsystem.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.assessmentsystem.assessmentsystem.dao.ExerciseDao;
import br.com.assessmentsystem.assessmentsystem.dao.MovementDao;
import br.com.assessmentsystem.assessmentsystem.model.Exercise;
import br.com.assessmentsystem.assessmentsystem.model.ExerciseChooser;
import br.com.assessmentsystem.assessmentsystem.model.Movement;
import br.com.assessmentsystem.assessmentsystem.model.Node;
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

	@RequestMapping(value = Routes.exercisesCreateDirectory, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Node createDirectory(HttpServletRequest request) {
		String path = request.getParameter("directory");

		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");

		String userId = Integer.toString(user.getId());

		String exerciseId = (String) session.getAttribute("exerciseId");

		File fileDirectory = new File("/usr/local/tomcat/students/" + userId + "/" + exerciseId + "/" + path);

		if (!path.contains("..")) {
			fileDirectory.mkdirs();
			if (path.contains(".")) {
				fileDirectory.delete();
				Exercise.writeFile("", fileDirectory);
			}
		}

		if (path.contains("..")) {
			System.out.println("Não é");
		}

		Node rootNode = updateDirectoryTree(request);

		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		System.out.println(gson.toJson(rootNode));

		//response.setStatus(HttpServletResponse.SC_OK);

		return rootNode;
	}

	@RequestMapping(Routes.exercisesCreateFile)
	public String createFile(String name) {
		return "";
	}

	public void directoryTree(File parentNode, Node node) {
		if (parentNode.isDirectory()) {

			//if (!(node.id.isEmpty())) {
				if (node.children == null) {
					node.children = new ArrayList<Node>();
				}

				node.id = parentNode.getName();

				node.text = parentNode.getName();

				File childNodes[] = parentNode.listFiles();
				for (File childNode : childNodes) {
					Node child = new Node();

					node.children.add(child);

					directoryTree(childNode, child);
				}
			//}
		} else {
			node.id = parentNode.getName();
			node.text = parentNode.getName();
		}
	}

	/*
	 * public void directoryTree(File dir, Node node) { File[] files =
	 * dir.listFiles(); for (File file : files) { if (file.isDirectory()) { if
	 * (node.children == null) { node.children = new ArrayList<Node>(); } Node child
	 * = new Node();
	 * 
	 * node.children.add(child);
	 * 
	 * directoryTree(file, child);
	 * 
	 * System.out.println("tFilho:"+node.children.size());
	 * 
	 * if(node.children.size() == 0) { node.children = null; }
	 * 
	 * }
	 * 
	 * node.id = file.getName();
	 * 
	 * node.text = file.getName(); System.out.println("NodeId:"+node.id);
	 * 
	 * if(node.id == null) { node = null; } //System.out.println("     file:" +
	 * file.getCanonicalPath()); } }
	 */
	public Node updateDirectoryTree(HttpServletRequest request) {
		System.out.println("La");

		Node rootNode = new Node();

		String path = request.getParameter("directory");

		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");

		String userId = Integer.toString(user.getId());

		String exerciseId = (String) session.getAttribute("exerciseId");

		File rootFile = new File("/usr/local/tomcat/students/" + userId + "/" + exerciseId);

		System.out.println("Antes");

		directoryTree(rootFile, rootNode);

		System.out.println("directoryTree");

		System.out.println(rootNode);

		return rootNode;
	}

	@RequestMapping(Routes.exercisesNew)
	public String newExercise(HttpServletRequest request, Model model) {
		String titulo = "";

		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");

		ExerciseDao exerciseDao = new ExerciseDao();

		int id = Integer.parseInt(request.getParameter("exerciseId"));

		Exercise exercise = exerciseDao.findById(id);

		session.setAttribute("title", exercise.getStatement());
		session.setAttribute("exerciseId", Integer.toString(exercise.getId()));
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

		// System.out.println("Exercicio:"+Integer.toString(user.getId()) );

		exercise.setCode(resolution);
		exercise.setUserId(user.getId());
		// exercise.createDirectory();

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
		model.addAttribute("exerciseId", request.getParameter("exerciseId"));

		if (exercise.hasCompileErrors != true) {
			// exercicio.salvarBancoDeDados(codigoUsuario, conexao);
			// if (chooser.canDoNextExercise() == true) {
			return "redirect:" + Routes.exercisesNew;
			// } else {
			// javax.swing.JOptionPane.showMessageDialog(null, "Parabéns. Você passou no
			// teste!");
			// return "redirect:" + Routes.main;
			// }
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
