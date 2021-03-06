/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.assessmentsystem.assessmentsystem.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.assessmentsystem.assessmentsystem.dao.ExerciseDao;
import br.com.assessmentsystem.assessmentsystem.dao.MovementDao;
import br.com.assessmentsystem.assessmentsystem.model.Exercise;
import br.com.assessmentsystem.assessmentsystem.model.ExerciseChooser;
import br.com.assessmentsystem.assessmentsystem.model.Movement;
import br.com.assessmentsystem.assessmentsystem.model.Node;
import br.com.assessmentsystem.assessmentsystem.model.Solution;
import br.com.assessmentsystem.assessmentsystem.model.User;

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

		String exerciseName = (String) session.getAttribute("exerciseName");	

		String createDir = Exercise.initialDirectory + userId + "/" + exerciseId + "/" + exerciseName + "/" + path;
		
		System.out.println("createDirectory:"+createDir);
		
		File fileDirectory = new File(createDir);
		
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

		/*
		 * GsonBuilder builder = new GsonBuilder(); Gson gson = builder.create();
		 * System.out.println(gson.toJson(rootNode));
		 */
		// response.setStatus(HttpServletResponse.SC_OK);

		return rootNode;
	}

	@RequestMapping(value = Routes.exercisesUpdateDirectoryTree, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Node updateDirectoryTreeHTML(HttpServletRequest request) {
		Node rootNode = updateDirectoryTree(request);

		return rootNode;
	}

	public void directoryTree(File parentNode, Node node, String pathNode) {
		if (parentNode.isDirectory()) {

			if (node.children == null) {
				node.children = new ArrayList<Node>();
			}

			node.id = pathNode + "_" + parentNode.getName();

			node.text = parentNode.getName();

			File childNodes[] = parentNode.listFiles();
			for (File childNode : childNodes) {
				Node child = new Node();

				node.children.add(child);

				directoryTree(childNode, child, node.id);
			}
		} else {
			node.id = pathNode + "_" + parentNode.getName();

			// System.out.println(node.id);

			node.text = parentNode.getName();
			node.icon = "ti-file";
		}
	}

	@RequestMapping(Routes.exercisesSaveFile)
	@ResponseBody
	public String saveFile(HttpServletRequest request) {
		String content = request.getParameter("content");

		if (!(content == null || content.length() == 0)) {
			HttpSession session = request.getSession();
			
			System.out.println("content:"+content);

			User user = (User) session.getAttribute("user");

			String userId = Integer.toString(user.getId());

			String exerciseId = (String) session.getAttribute("exerciseId");

			String exerciseName = (String) session.getAttribute("exerciseName");	
			
			String fileName = request.getParameter("fileName");

			fileName = fileName.replace("_", "/");

			String path = Exercise.initialDirectory + userId + "/"+ exerciseId + fileName;

			System.out.println("save:"+path);
			
			File fileDirectory = new File(path);

			Exercise.writeFile(content, fileDirectory);
		}

		return "saved";
	}

	@RequestMapping(Routes.exercisesOpenFile)
	@ResponseBody
	public String openFile(HttpServletRequest request) {
		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");

		String userId = Integer.toString(user.getId());

		String exerciseId = (String) session.getAttribute("exerciseId");

		String exerciseName = (String) session.getAttribute("exerciseName");	
		
		String fileName = request.getParameter("fileName");

		fileName = fileName.replace("_", "/");

		String path = Exercise.initialDirectory + userId + "/"+ exerciseId + fileName;

		System.out.println("open:" + path);

		String content = Exercise.openFile(path);

		return content;
	}

	public Node updateDirectoryTree(HttpServletRequest request) {
		Node rootNode = new Node();

		String path = request.getParameter("directory");

		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");

		String userId = Integer.toString(user.getId());

		String exerciseId = (String) session.getAttribute("exerciseId");
		
		String exerciseName = (String) session.getAttribute("exerciseName");
		
		File rootFile = new File(Exercise.initialDirectory + userId + "/" + exerciseId + "/" + exerciseName);

		directoryTree(rootFile, rootNode, "");

		return rootNode;
	}

	@RequestMapping(Routes.exercisesNew)
	public String newExercise(HttpServletRequest request, Model model) {
		String titulo = "";

		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");
		
		try {
			FileUtils.deleteDirectory(new File(Exercise.initialDirectory + Integer.toString(user.getId())));
		} catch (IOException e) {
			// TODO Auto-generated catch block //e.printStackTrace();
		}
		
		ExerciseDao exerciseDao = new ExerciseDao();

		int id = Integer.parseInt(request.getParameter("exerciseId"));

		Exercise exercise = exerciseDao.findById(id);

		session.setAttribute("title", exercise.getStatement());
		session.setAttribute("exerciseId", Integer.toString(exercise.getId()));
		session.setAttribute("exerciseName", exercise.getName());
		session.setAttribute("useDirectoryTree", Integer.toString(exercise.getUseDirectoryTree()));

		
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
