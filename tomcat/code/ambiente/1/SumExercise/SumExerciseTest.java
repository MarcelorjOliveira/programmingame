package 1/SumExercise;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import SumExercise.SumExercise;

public class SumExerciseTest {
    
   private SumExercise sumExercise = new SumExercise();

//this.tests

    @Test
    public void testMethods() {
        assertEquals(5.0f, sumExercise.soma(2,3), 0.0);
    }
   
    @Test
    public void testAttributes() {
        assertEquals(10.0f, sumExercise.soma(4,6), 0.0);
    }
}
