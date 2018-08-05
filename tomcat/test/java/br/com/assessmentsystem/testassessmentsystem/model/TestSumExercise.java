/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.assessmentsystem.testassessmentsystem.model;

import br.com.assessmentsystem.assessmentsystem.model.Exercise;
import br.com.assessmentsystem.assessmentsystem.model.SumExercise;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marcelooliveira
 */
public class TestSumExercise {
    
    private Exercise sumExercise = new SumExercise();

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testSum() {
        sumExercise.buildGrading("int soma(int x, int y) {\n"
                + "return -10; \n"
                + "}");
        assertEquals(0.0f, sumExercise.exerciseMark(), 0.0);
    }
    
    @Test
    public void testSumCorrect() {
        sumExercise.buildGrading("int soma(int x, int y) {\n"
                + "return (x + y); \n"
                + "}");
        assertEquals(10.0f, sumExercise.exerciseMark(), 0.0);
    }
}   
