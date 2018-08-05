/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.assessmentsystem.testassessmentsystem.model;

import br.com.assessmentsystem.assessmentsystem.model.Exercise;
import br.com.assessmentsystem.assessmentsystem.model.SummationExercise;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marcelo
 */
public class TestSummationExercise {
    
    private Exercise summationExercise = new SummationExercise();

    @Test
    public void testMultiple1() {
        summationExercise.buildGrading("int somatorio(int x, int y) {\n"
                + "return 0; \n"
                + "}");
        assertEquals(0.0f, summationExercise.exerciseMark(), 0.0);
    }
    
    @Test
    public void testMultiple2() {
        summationExercise.buildGrading("int somatorio(int x, int y) {\n"
                + "return 1; \n"
                + "}");
        assertEquals(0.0f, summationExercise.exerciseMark(), 0.0);
        }
        
    @Test
    public void testAllMultiple() {
        summationExercise.buildGrading("int somatorio(int parametro1, int parametro2)\n"
                + "{\n"
                + "int soma=0, contador;\n "
                + "for(contador = parametro1 ; contador <= parametro2 ; contador++)\n"
                + "{\n"
                + "soma += contador;\n"
                + "}\n"
                + "return soma;\n"
                + "}");  
        assertEquals(10.0f, summationExercise.exerciseMark(), 0.0);
    }

}
