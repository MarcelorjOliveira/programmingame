/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.assessmentsystem.testassessmentsystem.model;

import br.com.assessmentsystem.assessmentsystem.model.Exercise;
import br.com.assessmentsystem.assessmentsystem.model.FactorialExercise;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marcelo
 */
public class TestFactorialExercise {
    
    private Exercise factorialExercise = new FactorialExercise();

    @Test
    public void testFactorial() {
        factorialExercise.buildGrading("int fatorial(int x) {\n"
                + "return 0; \n"
                + "}");
        assertEquals(0.0f, factorialExercise.exerciseMark(), 0.0);
    }
    
    @Test
    public void testCondition1Factorial() {
        factorialExercise.buildGrading("int fatorial(int x) {\n"
                + "return 1; \n"
                + "}");
        assertEquals(4.0f, factorialExercise.exerciseMark(), 0.0);
        }
        
    @Test
    public void testAllFactorial() {
        factorialExercise.buildGrading("       int fatorial(int n) \n" +
                                       "         {\n" +
                                       "              int fat, i;\n" +
                                       "              fat = 1;\n" +
                                       "              for (i = 1; i <= n; i = i + 1) \n" +
                                       "              {\n" +
                                       "                  fat = fat * i;\n" +
                                       "              }\n" +
                                       "              return fat;\n" +
                                       "         }\n");  
        assertEquals(10.0f, factorialExercise.exerciseMark(), 0.0);
    }
  
}
