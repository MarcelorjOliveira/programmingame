package br.com.assessmentsystem.assessmentsystem.model;

/*
 * @author marcelo
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;

import br.com.assessmentsystem.assessmentsystem.dao.MovementDao;
import br.com.assessmentsystem.assessmentsystem.dao.SolutionDao;

public class OOJavaExercise extends Exercise {

	private String codeRunner;
	protected String root;

	public void initializeExercise() {
		nameTestFile = "testMethod" + name;
		String programTests = nameTestFile + ".c";
		testFile = new File(programTests);
	}

	public void buildGrading(String code) {
		root = Integer.toString(this.userId) + "/" + name;

		File fileDirectory = new File(initialDirectory + Integer.toString(this.userId));

		fileDirectory.mkdir();

		fileDirectory = new File(initialDirectory + root);

		fileDirectory.mkdir();

		String codeWorked = "package " + name + "; \n" + this.code;

		// createDirectory(Integer.toString(userId));

		// createDirectory(root);

		File textFile = new File(initialDirectory + root + "/" + name + ".java");

		writeFile(codeWorked, textFile);

		textFile = new File(initialDirectory + root + "/" + name + "Test.java");

		String codeTestWorked = this.tests;
		// capturar do banco de dados
		// String codeTestWorked = "";
		// this.testCode;

		writeFile(codeTestWorked, textFile);

		String codeRunner = "package " + name + ";\n" + "\n" + "import org.junit.runner.JUnitCore;\n"
				+ "import org.junit.runner.Result;\n" + "import org.junit.runner.notification.Failure;\n"
				+ "import "+name+"."+name+"Test;\n" + "\n" + "public class "+name+"Runner{\n"
				+ "   public static void main(String[] args){\n"
				+ "      Result result = JUnitCore.runClasses("+name+"Test.class);\n" + "		\n"
				+ "      for (Failure failure : result.getFailures()) {\n"
				+ "         System.out.println(failure.getMessage());\n" + "      }\n" + "		\n"
				+ "      System.out.println(result.wasSuccessful());\n" + "   }\n" + "}  \n";

		// Pegar do banco de dados
		// String codeRunner = "";

		textFile = new File(initialDirectory + root + "/"+name+"Runner.java");

		writeFile(codeRunner, textFile);

		buildSourceCode();
		
		try {
			FileUtils.deleteDirectory(new File(initialDirectory + Integer.toString(this.userId)));
		} catch (IOException e) {
			// TODO Auto-generated catch block //e.printStackTrace();
		}

	}

	public void buildCodeTest() {

		// teste
	}

	public void buildSourceCode() {
		try {
			// Compilação do código-fonte
			String nameWithDirectory = initialDirectory + root + "/" + name;
			String nameCommand = "javac " + nameWithDirectory + ".java " + nameWithDirectory + "Test.java "
					+ nameWithDirectory + "Runner.java";
			// java SumExercise.SumExerciseRunner
			System.out.println(nameCommand);
			Process process = Runtime.getRuntime().exec(nameCommand);

			String exerciseClassPath = initialDirectory + Integer.toString(userId);

			nameCommand = "java -cp " + exerciseClassPath + " " + name + "." + name + "Runner";

			System.out.println(nameCommand);

			String result = "";

			while (result.length() == 0) {
				Process process2 = Runtime.getRuntime().exec(nameCommand);

				BufferedReader input = new BufferedReader(new InputStreamReader(process2.getInputStream()));
				result = "";
				String line = null;
				while ((line = input.readLine()) != null) {
					result += line;
				}
				input.close();

			}

			System.out.println(result);

			SolutionDao solutionDao = new SolutionDao();

			List<Solution> solutions = solutionDao.getByIdExercise(id);

			List<Solution> solutionsStudentWrong = new ArrayList<Solution>();

			double failures = 0;

			for (int c = 0; c < solutions.size(); c++) {
				if (result.indexOf(solutions.get(c).getTest()) != -1) {
					failures++;
					solutionsStudentWrong.add(solutions.get(c));
				}

			}

			this.solutions = solutionsStudentWrong;
			
			double solutionSize = solutions.size() ; 

			//System.out.println("SolutionsSize:"+solutions.size() );
			//System.out.println("Failures:"+failures);
			this.testMark = ((solutionSize - failures) / solutionSize) * 100;
			//System.out.println("TestMark:"+String.valueOf(this.testMark));
			this.testMark = Math.round(this.testMark);
			//System.out.println("TestMark:"+String.valueOf(this.testMark));
			this.testMark = this.testMark / 10;
			//System.out.println("TestMark:"+String.valueOf(this.testMark));
			
			tries = 0;
			// javax.swing.JOptionPane.showMessageDialog(null, "Nota testes de unidade : " +
			// testMark);

			// javax.swing.JOptionPane.showMessageDialog(null, "Nota Exercício : " +
			// exerciseMark);
			// javax.swing.JOptionPane.showMessageDialog(null, "Exercicio enviado com
			// sucesso. Vamos fazer o próximo exercicio");
			hasCompileErrors = false;

			// }

			// testFile.delete();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void assertProgram() {

	}

	protected double markExercise(Document doc, XPath xpath) throws XPathExpressionException {
		MovementDao dao = new MovementDao();
		Movement movement = new Movement();

		XPathExpression expression = xpath
				.compile("/CUNIT_TEST_RUN_REPORT/CUNIT_RUN_SUMMARY/CUNIT_RUN_SUMMARY_RECORD[3]/SUCCEEDED");

		double correct = (Double) expression.evaluate(doc, XPathConstants.NUMBER);

		expression = xpath.compile("/CUNIT_TEST_RUN_REPORT/CUNIT_RUN_SUMMARY/CUNIT_RUN_SUMMARY_RECORD[3]/RUN");

		double total = (Double) expression.evaluate(doc, XPathConstants.NUMBER);
		this.testMark = (correct / total) * 100;
		this.testMark = Math.round(this.testMark);
		this.testMark = this.testMark / 10;

		System.out.println("Correct:" + correct);
		System.out.println("Total:" + total);

		// this.testMark = correct;

		return this.testMark;
	}

	private void calcExerciseMark() {
		exerciseMark += testMark;
	}

	public String getCodeRunner() {
		return codeRunner;
	}

	public void setCodeRunner(String codeRunner) {
		this.codeRunner = codeRunner;
	}

}
