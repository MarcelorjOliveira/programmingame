package testGame;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import testGame.TelevisaoTest;

public class RunnerTelevisao{
   public static void main(String[] args){
      Result result = JUnitCore.runClasses(TelevisaoTest.class);
		
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.getMessage());
      }
		
      System.out.println(result.wasSuccessful());
   }
}  
