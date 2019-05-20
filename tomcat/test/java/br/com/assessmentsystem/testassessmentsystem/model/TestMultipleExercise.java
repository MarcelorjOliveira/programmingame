/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.assessmentsystem.testassessmentsystem.model;

import br.com.assessmentsystem.assessmentsystem.model.Exercise;
import br.com.assessmentsystem.assessmentsystem.model.MultipleExercise;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marcelo
 */
public class TestMultipleExercise {
    
    private Exercise multipleExercise = new MultipleExercise();

    @Test
    public void testMultiple1() {
        multipleExercise.buildGrading("int multiplo(int x, int y) {\n"
                + "return 0; \n"
                + "}");
        assertEquals(6.0f, multipleExercise.exerciseMark(), 0.0);
    }
    
    @Test
    public void testMultiple2() {
        multipleExercise.buildGrading("int multiplo(int x, int y) {\n"
                + "return 1; \n"
                + "}");
        assertEquals(4.0f, multipleExercise.exerciseMark(), 0.0);
        }
        
    @Test
    public void testAllMultiple() {
        multipleExercise.buildGrading("int multiplo(int divisor, int dividendo) \n"
              + "{\n"
        + "if(divisor % dividendo == 0)\n "
        + "{\n"
        + "   return 0;\n"
        + "}\n"
        + "else\n"
        + "{\n"
        + "   return 1 ;\n"
        + "}\n"
        + "}");  
        assertEquals(10.0f, multipleExercise.exerciseMark(), 0.0);
    }

}
