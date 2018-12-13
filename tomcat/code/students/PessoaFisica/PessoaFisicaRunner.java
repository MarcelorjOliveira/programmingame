package PessoaFisica;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import PessoaFisica.PessoaFisicaTest;

public class PessoaFisicaRunner{
   public static void main(String[] args){
      Result result = JUnitCore.runClasses(PessoaFisicaTest.class);
		
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.getMessage());
      }
		
      System.out.println(result.wasSuccessful());
   }
}  
