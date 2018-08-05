/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.assessmentsystem.testassessmentsystem.model;

import br.com.assessmentsystem.assessmentsystem.model.Exercise;
import br.com.assessmentsystem.assessmentsystem.model.MultiplicandExercise;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marcelo
 */
public class TestMultiplicandExercise {
    
    private Exercise multiplicandExercise = new MultiplicandExercise();

    @Test
    public void testMultiple1() {
        multiplicandExercise.buildGrading("int produtorio(int x, int y) {\n"
                + "return 0; \n"
                + "}");
        assertEquals(2.0f, multiplicandExercise.exerciseMark(), 0.0);
    }
    
    @Test
    public void testMultiple2() {
        multiplicandExercise.buildGrading("int produtorio(int x, int y) {\n"
                + "return 1; \n"
                + "}");
        assertEquals(0.0f, multiplicandExercise.exerciseMark(), 0.0);
        }
        
    @Test
    public void testAllMultiple() {
        multiplicandExercise.buildGrading("int produtorio(int parametro1, int parametro2)\n"
                + "{\n"
                + "int produto=1, contador;\n "
                + "for(contador = parametro1 ; contador <= parametro2 ; contador++)\n"
                + "{\n"
                + "produto *= contador;\n"
                + "}\n"
                + "return produto;\n"
                + "}");  
        assertEquals(10.0f, multiplicandExercise.exerciseMark(), 0.0);
    }

}
