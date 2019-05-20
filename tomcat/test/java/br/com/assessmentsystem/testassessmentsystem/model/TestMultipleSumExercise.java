/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.assessmentsystem.testassessmentsystem.model;

import br.com.assessmentsystem.assessmentsystem.model.Exercise;
import br.com.assessmentsystem.assessmentsystem.model.MultipleSumExercise;
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
public class TestMultipleSumExercise {

    private Exercise multipleSum = new MultipleSumExercise();
    
    @Test
    public void testMultipleSum1() {
        multipleSum.buildGrading("int somaMultiplos(int x, int y) {\n"
                + "return 0; \n"
                + "}");
        assertEquals(0.0f, multipleSum.exerciseMark(), 0.0);
    }
    
    @Test
    public void testMultipleSum2() {
        multipleSum.buildGrading("int somaMultiplos(int x, int y) {\n"
                + "return 1; \n"
                + "}");
        assertEquals(0.0f, multipleSum.exerciseMark(), 0.0);
        }
        
    @Test
    public void testAllMultipleSum() {
        multipleSum.buildGrading("int somaMultiplos(int multiplo, int parametro) \n"
                + "{ \n"
                + "int auxiliar=1,resultadosomaMultiplos = 0; \n"
                + "while(multiplo * auxiliar <= parametro) \n"
                + "{ \n"
                + "resultadosomaMultiplos += multiplo * auxiliar; \n"
                + "auxiliar++; \n"
                + "} \n"
                + "return resultadosomaMultiplos; \n"
                + "} \n  ");  
        assertEquals(10.0f, multipleSum.exerciseMark(), 0.0);
    }
}
