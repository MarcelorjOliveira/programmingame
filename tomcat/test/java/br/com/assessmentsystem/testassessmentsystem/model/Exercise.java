package br.com.assessmentsystem.assessmentsystem.model;


/*
 * @author marcelo
 */
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;

public abstract class Exercise {

    //Criar um construtor
    int DEFAULT_BUFFER_SIZE = 255;
    protected String name, exercise;
    protected String testCode;
    protected String code;
    protected String modelResponse;
    protected String nameTestFile;
    protected File testFile;
    protected String nameMetricsFile;
    protected File metricsFile;
    protected File modelResponseFile = new File("modelResponse.c");
    public boolean hasCompileErrors = false;
    public boolean endOfAttempts = false;
    protected int countAttempts = 1;
    protected double markMetricsStudent, markMetricsModel;
    protected double testMark;
    protected double numerLineCodes, cyclomaticComplex, countReservedWords;
    protected double metricsMark;
    protected double exerciseMark;

    public Exercise(String name, String exercise) {
        //TODO o Exercício tem que ter um campo String para enunciado
        this.name = name;
        this.exercise = exercise;
        nameTestFile = "testMethod" + name;
        nameMetricsFile = "methodMetrics" + name;
        String programTests = nameTestFile + ".c";
        testFile = new File(programTests);
        String programMetrics = nameMetricsFile + ".c";
        metricsFile = new File(programMetrics);
    }

    public String title() {
        return exercise;
    }

    public void writeFiles() {
        writeFile(testCode, testFile);
        writeFile(code, metricsFile);
        writeFile(modelResponse, modelResponseFile);
    }

    public abstract void buildGrading(String code);

    public void buildCodeTest(String code, String tests) {
        this.testCode =
                "#include <stdio.h> \n"
                + "#include <stdlib.h> \n"
                + "#include \"CUnit/Basic.h\" \n"
                + code
                + "int init_suite(void) { \n"
                + "return 0; \n"
                + "} \n"
                + "int clean_suite(void) { \n"
                + "return 0; \n"
                + "} \n"
                + "void testa" + name + "() { \n"
                + tests
                + "} \n"
                + "int main() { \n"
                + "CU_pSuite pSuite = NULL; \n"
                + "    if (CUE_SUCCESS != CU_initialize_registry()) \n"
                + "return CU_get_error(); \n"
                + "    pSuite = CU_add_suite(\"newcunittest\", init_suite, clean_suite); \n"
                + "if (NULL == pSuite) { \n"
                + "CU_cleanup_registry(); \n"
                + "return CU_get_error(); \n"
                + "} \n"
                + "if (NULL == CU_add_test(pSuite, \"testa" + name + "\", testa" + name + ")) { \n"
                + "CU_cleanup_registry(); \n"
                + "return CU_get_error(); \n"
                + "} \n"
                + "CU_basic_set_mode(CU_BRM_VERBOSE); \n"
                + "CU_automated_run_tests(); \n"
                + "CU_cleanup_registry(); \n"
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

    //O Número de linhas do programa
    public void buildSourceCode() {
        try {
            //Compilação do código-fonte
            String nameCommand = "gcc " + nameTestFile + ".c" + " -lcunit -o " + nameTestFile;
            Process process = Runtime.getRuntime().exec(nameCommand);

            BufferedReader input = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String result = "";
            String line = null;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();

            if (!result.equals("")) {
                //javax.swing.JOptionPane.showMessageDialog(null, "Tem erro de compilação");
                hasCompileErrors = true;
                if (countAttempts < 3) {
                    exerciseMark -= 0.5;
                    countAttempts += 1;
                } else {
                    endOfAttempts = true;
                }
            } else {
                assertProgram();
                //javax.swing.JOptionPane.showMessageDialog(null, "Nota testes de unidade : " + testMark);
                if (testMark == 0) {
                    exerciseMark = 0;
                } else {
                    //calculoDasMetricasEComparacaoComAsDaRespostaModelo();
                    calcExerciseMark();
                }
                //javax.swing.JOptionPane.showMessageDialog(null, "Nota Exercício : " + exerciseMark);
                //javax.swing.JOptionPane.showMessageDialog(null, "Exercicio enviado com sucesso. Vamos fazer o próximo exercicio");
                hasCompileErrors = false;

            }

            testFile.delete(); 
            metricsFile.delete();
            modelResponseFile.delete();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract double markExercise(Document doc, XPath xpath) throws XPathExpressionException;

    private void assertProgram() {
        File resultTests = null;
        try {
            String nameCommand = "./" + nameTestFile;
            Process process = Runtime.getRuntime().exec(nameCommand);
        } catch (IOException ex) {
            Logger.getLogger(Exercise.class.getName()).log(Level.SEVERE, null, ex);
        }

        resultTests = new File("CUnitAutomated-Results.xml");

        //Porque dava problema de Exceção
        FileEditor.removeLineFile("CUnitAutomated-Results.xml",
                "<!DOCTYPE CUNIT_TEST_RUN_REPORT SYSTEM \"CUnit-Run.dtd\">");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder documentConstructor;
        try {
            documentConstructor = factory.newDocumentBuilder();
            Document doc = documentConstructor.parse(resultTests);
            XPath xpath = XPathFactory.newInstance().newXPath();

            //TODO se notaTestes = 0 , tem que zerar tudo

            testMark = markExercise(doc, xpath);
            String nameFile = "./" + nameTestFile;
            File executable = new File(nameFile);
            executable.delete();
            resultTests.delete();

        } catch (Exception ex) {
            assertProgram();
        }
    }

    private void calcExerciseMark() {
        exerciseMark += testMark;
    }

    public double exerciseMark() {
        return exerciseMark;
    }

    public void saveResponseWithCompileErrors(int userCode, Connection connection) {
        try {
            String sql = "insert into Movimentos (codUsuarioMovimento,codigoUsado) "
                    + "values (?,?) ";
            PreparedStatement sentence = connection.prepareStatement(sql);
            sentence.setInt(1, userCode);
            sentence.setString(2, code);
            sentence.execute();
            sentence.close();
        } catch (Exception e) {
            //javax.swing.JOptionPane.showMessageDialog(null, e);
        }
    }

    public void saveResponse(int userCode, Connection connection) {
        try {
            String sql = "insert into Movimentos (codUsuarioMovimento, codigoUsado, notaTesteUnidade,"
                    + "nomeExercicio, numeroLinhasPrograma, complexidadeCiclomatica, quantidadePalavrasReservadas"
                    + ",notaMetricasExercicio, notaExercicio) values (?,?,?,?,?,?,?,?,?) ";
            PreparedStatement sentence = connection.prepareStatement(sql);
            sentence.setInt(1, userCode);
            sentence.setString(2, code);
            sentence.setDouble(3, testMark);
            sentence.setString(4, name);
            sentence.setDouble(5, numerLineCodes);
            sentence.setDouble(6, cyclomaticComplex);
            sentence.setDouble(7, countReservedWords);
            sentence.setDouble(8, metricsMark);
            sentence.setDouble(9, exerciseMark);
            sentence.execute();
            sentence.close();
        } catch (Exception e) {
            //javax.swing.JOptionPane.showMessageDialog(null, e);
        }
    }
}