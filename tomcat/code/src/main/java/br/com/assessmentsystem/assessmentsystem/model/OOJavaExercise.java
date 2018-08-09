package br.com.assessmentsystem.assessmentsystem.model;

/*
 * @author marcelo
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import br.com.assessmentsystem.assessmentsystem.dao.MovementDao;

public class OOJavaExercise extends Exercise {

	protected File testFile;

	// Criar um construtor
	int DEFAULT_BUFFER_SIZE = 255;
	protected int id;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	protected String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	protected int tries;

	protected String statement;

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String getStatement() {
		return statement;
	}

	protected String modelResponse;

	public void setModelResponse(String modelResponse) {
		this.modelResponse = modelResponse;
	}

	public String getModelResponse() {
		return modelResponse;
	}

	protected String tests;

	public void setTests(String tests) {
		this.tests = tests;
	}

	public String getTests() {
		return tests;
	}

	protected String code;

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public boolean hasCompileErrors = false;
	public boolean endOfAttempts = false;
	protected int countAttempts = 1;

	protected String nameTestFile;

	public double exerciseMark;

	protected double testMark;

	public void setTestMark(double testMark) {
		this.testMark = testMark;
	}

	public double getTestMark() {
		return this.testMark;
	}

	public String testCode;

	public void initializeExercise() {
		nameTestFile = "testMethod" + name;
		String programTests = nameTestFile + ".c";
		testFile = new File(programTests);
	}

	public void buildGrading(String code) {
		initializeExercise();
		this.code = code;

		buildCodeTest();

		writeFile(testCode, testFile);
		
		buildSourceCode();
	}

	public void buildCodeTest() {
		
		this.testCode = 
"import org.junit.After;\n"+
"import org.junit.AfterClass;\n"+
"import org.junit.Before;\n"+
"import org.junit.BeforeClass;\n"+
"import org.junit.Test;\n"+
"import static org.junit.Assert.*;\n"+

"public class TestOOJava {\n"+
    
"    private Exercise sumExercise = new SumExercise();\n"+

//this.tests

"    @Test\n"+
"    public void testMethods() {"+
"        sumExercise.buildGrading(\"int soma(int x, int y) {\n"+
"                + \"return -10; \n"+
"                + \"}\");"+
"        assertEquals(0.0f, sumExercise.exerciseMark(), 0.0);"+
"    }"+
    
"    @Test"+
"    public void testAttributes() {"+
"        sumExercise.buildGrading(\"int soma(int x, int y) {\n"+
"                + \"return (x + y); \n"+
"                + \"}\");"+
"        assertEquals(10.0f, sumExercise.exerciseMark(), 0.0);"+
"    }"+
"}" 

+ this.code + 
	"Class classe = Conta.class; "+
	"for (Field atributo : classe.getDeclaredFields()) { " +
	" System.out.println(atributo.getName());      "+
	" } "
				+ "int init_suite(void) { \n" + "return 0; \n" + "} \n" + "int clean_suite(void) { \n" + "return 0; \n"
				+ "} \n" + "void testa" + name + "() { \n" + this.tests + "} \n" + "int main() { \n"
				+ "CU_pSuite pSuite = NULL; \n" + "    if (CUE_SUCCESS != CU_initialize_registry()) \n"
				+ "return CU_get_error(); \n"
				+ "    pSuite = CU_add_suite(\"newcunittest\", init_suite, clean_suite); \n"
				+ "if (NULL == pSuite) { \n" + "CU_cleanup_registry(); \n" + "return CU_get_error(); \n" + "} \n"
				+ "if (NULL == CU_add_test(pSuite, \"testa" + name + "\", testa" + name + ")) { \n"
				+ "CU_cleanup_registry(); \n" + "return CU_get_error(); \n" + "} \n"
				+ "CU_basic_set_mode(CU_BRM_VERBOSE); \n" + "CU_automated_run_tests(); \n" + "CU_cleanup_registry(); \n"
				+ "return CU_get_error(); \n" 
				+ "} \n"; 
	}

	public void writeFile(String code, File file) {
		try {
			// Gravando no arquivo
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(code.getBytes());
			fos.close();
		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}

	public void buildSourceCode() {
		try {
			// Compilação do código-fonte
			String nameCommand = "gcc " + nameTestFile + ".c" + " -lcunit -o " + nameTestFile;
			Process process = Runtime.getRuntime().exec(nameCommand);

			BufferedReader input = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			String result = "";
			String line = null;
			while ((line = input.readLine()) != null) {
				result += line;
			}
			input.close();

			// javax.swing.JOptionPane.showMessageDialog(null, "Teste:"+result);

			/*
			 * if (!result.equals("")) { // javax.swing.JOptionPane.showMessageDialog(null,
			 * "Tem erro de compilação"); hasCompileErrors = true; if (countAttempts < 3) {
			 * exerciseMark -= 0.5; countAttempts += 1; } else { endOfAttempts = true; } }
			 * else {
			 */
			tries = 0;
			assertProgram();
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
		File resultTests = null;
		String everything;
		try {
			String nameCommand = "./" + nameTestFile;
			Process process = Runtime.getRuntime().exec(nameCommand);
		} catch (IOException ex) {
			Logger.getLogger(Exercise.class.getName()).log(Level.SEVERE, null, ex);
		}

		resultTests = new File("CUnitAutomated-Results.xml");
		
		// Porque dava problema de Exceção
		/*FileEditor.removeLineFile("CUnitAutomated-Results.xml",
				"<!DOCTYPE CUNIT_TEST_RUN_REPORT SYSTEM \"CUnit-Run.dtd\">");
		*/

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder documentConstructor;
		try {
			BufferedReader br = new BufferedReader(new FileReader("CUnitAutomated-Results.xml"));
			try {
			    StringBuilder sb = new StringBuilder();
			    String line = br.readLine();

			    while (line != null) {
			        sb.append(line);
			        sb.append(System.lineSeparator());
			        line = br.readLine();
			    }
			    everything = sb.toString();
			} finally {
			    br.close();
			}
			
			everything = everything.replaceAll("<!DOCTYPE CUNIT_TEST_RUN_REPORT SYSTEM \"CUnit-Run.dtd\">", "");
			
			//System.out.println(everything);
			
			InputSource source = new InputSource(new StringReader(everything));
			
			documentConstructor = factory.newDocumentBuilder();
			Document doc = documentConstructor.parse(source);
			XPath xpath = XPathFactory.newInstance().newXPath();
			
			testMark = markExercise(doc, xpath);
			
			String nameFile = "./" + nameTestFile;
			File executable = new File(nameFile);
			executable.delete();
			resultTests.delete();

		} catch (Exception ex) {
			if (tries < 10) {
				tries += 1;
				assertProgram();
			} else
				testMark = 0;
		}
	}

	protected double markExercise(Document doc, XPath xpath) throws XPathExpressionException {
		MovementDao dao = new MovementDao();
		Movement movement = new Movement();

		XPathExpression expression = xpath
				.compile("/CUNIT_TEST_RUN_REPORT/CUNIT_RUN_SUMMARY/CUNIT_RUN_SUMMARY_RECORD[3]/SUCCEEDED");
		
		double correct = (Double) expression.evaluate(doc, XPathConstants.NUMBER); 
		
		expression = xpath.compile("/CUNIT_TEST_RUN_REPORT/CUNIT_RUN_SUMMARY/CUNIT_RUN_SUMMARY_RECORD[3]/RUN");

		
		double total = (Double) expression.evaluate(doc, XPathConstants.NUMBER); 
		this.testMark = (correct/total)*100; 
		this.testMark = Math.round(this.testMark);
		this.testMark = this.testMark/10;
		
		System.out.println("Correct:"+correct);
		System.out.println("Total:"+total);
		
		//this.testMark = correct;
		
		return this.testMark;
	}

	private void calcExerciseMark() {
		exerciseMark += testMark;
	}

}
