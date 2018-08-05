/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.assessmentsystem.testassessmentsystem.model;

import br.com.assessmentsystem.assessmentsystem.model.Exercise;
import br.com.assessmentsystem.assessmentsystem.model.SpotExercise;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marcelo
 */
public class TestSpotExercise {
    
    private Exercise spotExercise = new SpotExercise();

    @Test
    public void testSpot1() {
        spotExercise.buildGrading("float lanchonete(int codigo, int quantidade) {\n"
                + "return 2; \n"
                + "}");
        assertEquals(1.0f, spotExercise.exerciseMark(), 0.0);
    }
    
    @Test
    public void testSpot2() {
        spotExercise.buildGrading("float lanchonete(int codigo, int quantidade) {\n"
                + "return 3.5 * quantidade; \n"
                + "}");
        assertEquals(2.0f, spotExercise.exerciseMark(), 0.0);
        }
    
    @Test
    public void testSpot3() {
        spotExercise.buildGrading("float lanchonete(int codigo, int quantidade) {\n"
                + "return 1; \n"
                + "}");
        assertEquals(0.0f, spotExercise.exerciseMark(), 0.0);
        }

        
    @Test
    public void testAllSpot() {
        spotExercise.buildGrading("float lanchonete(int codigo, int quantidade)\n"
               + "{\n"
            + "if(codigo == 1)\n"
            + "{\n"
            +    "return 2 * quantidade;\n"
            + "}\n"
            + "if(codigo == 2)\n"                          
            + "{\n"
            +   "return 2.50 * quantidade;\n"
            + "}\n"
            + "if(codigo == 3)\n"
            + "{\n"
            +    "return 5 * quantidade;\n"
            + "}\n"
            + "if(codigo == 4)\n"
            + "{\n"
            +   "return 3.50 * quantidade;\n"
            + "}\n"
            + "if(codigo == 5)\n"
            + "{\n"
            +   "return 8.50 * quantidade;\n"
            + "}"
            + "}");  
        assertEquals(10.0f, spotExercise.exerciseMark(), 0.0);
    }
}
