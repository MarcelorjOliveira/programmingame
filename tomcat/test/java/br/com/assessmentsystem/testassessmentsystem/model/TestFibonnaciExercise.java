/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.assessmentsystem.testassessmentsystem.model;

import br.com.assessmentsystem.assessmentsystem.model.Exercise;
import br.com.assessmentsystem.assessmentsystem.model.FibonnaciExercise;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marcelo
 */
public class TestFibonnaciExercise {
    
     private Exercise fibonnaciExercise = new FibonnaciExercise();

    @Test
    public void testFibonnaci() {
        fibonnaciExercise.buildGrading("int fibonnaci(int x) {\n"
                + "return 0; \n"
                + "}");
        assertEquals(0.0f, fibonnaciExercise.exerciseMark(), 0.0);
    }
    
    @Test
    public void testCondition1Fibonnaci() {
        fibonnaciExercise.buildGrading("int fibonnaci(int x) {\n"
                + "return 1; \n"
                + "}");
        assertEquals(4.0f, fibonnaciExercise.exerciseMark(), 0.0);
        }
        
    @Test
    public void testAllFactorial() {
        fibonnaciExercise.buildGrading("int fibonnaci(int termo) \n "
                + "{ \n"
                + "int resultado,anterior,i; \n"
                + "if((termo == 1) || (termo == 2)) \n"
                + "{ \n"
                + "return 1; \n"
                + "} \n"
                + "else \n"
                + "{ \n"
                + "resultado = 1; \n"
                + "anterior = 1; \n"
                + "for(i = 3 ; i <= termo; i++) \n"
                + "{ \n"
                + "resultado = resultado + anterior; \n"
                + "anterior = resultado - anterior;  \n"
                + "} \n"
                + "return resultado; \n"
                + "} \n"
                + "}");  
        assertEquals(10.0f, fibonnaciExercise.exerciseMark(), 0.0);
    }
  
}
