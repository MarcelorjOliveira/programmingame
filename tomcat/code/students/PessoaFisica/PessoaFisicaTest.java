package PessoaFisica;



import org.junit.After;

import org.junit.AfterClass;

import org.junit.Before;

import org.junit.BeforeClass;

import org.junit.Test;

import static org.junit.Assert.*;

import PessoaFisica.PessoaFisica;



public class PessoaFisicaTest {



    @Test
    public void testHipotese1() {
	String parametro = "31350876461";
	PessoaFisica pessoa = new PessoaFisica(parametro);

	System.out.println(parametro);	

        assertTrue(pessoa.valida(parametro));
    }

}
