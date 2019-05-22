package SumExercise;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import SumExercise.SumExerciseTest;

public class SumExerciseRunner{
   public static void main(String[] args){
      Result result = JUnitCore.runClasses(SumExerciseTest.class);
		
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.getMessage());
      }
		
      System.out.println(result.wasSuccessful());
   }
}  
	
