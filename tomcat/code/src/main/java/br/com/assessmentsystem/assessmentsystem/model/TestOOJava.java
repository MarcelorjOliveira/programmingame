package br.com.assessmentsystem.assessmentsystem.model;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.Test;

public class TestOOJava {

	/*
	 * Crie uma classe Conta.Ela deve ter o nome do titular (String), 
	 * o número (int), a agência (String), o saldo (double) e data_abertura (String).
	 * Além disso deve implementar os métodos saca e deposita
	 */
//this.tests
	@Test
    public void testAttributes() {
		ArrayList<String> fields = new ArrayList<String>();
		fields.add("titular"); fields.add("numero"); fields.add("agencia");  fields.add("saldo"); 
		fields.add("data_abertura");
		
    	Class classe = Conta.class;
		for (Field atributo : classe.getDeclaredFields()) {
		  if(fields.contains(atributo.getName())) {
			  fields.remove(atributo.getName());
		  }
		}
		
    	assertEquals(true, fields.isEmpty());
    }	
	
    @Test
    public void testMethods() {
    	ArrayList<String> methods = new ArrayList<String>();
		
    	Class classe = Conta.class;
		for (Method method : classe.getDeclaredMethods()) {
		  if(methods.contains(method.getName())) {
			  methods.remove(method.getName());
		  }
		}
		
        assertEquals(true, methods.isEmpty());
    }
    
    @Test
    public void testMethodSaca() {
    	Conta conta = new Conta();
    	conta.saldo = 700.00;
    	conta.saca(500.00);
    	assertEquals(200.00, conta.saldo);
    	conta.saca(100.94);
    	assertEquals(99.06, conta.saldo);
    }
    
    @Test
    public void testMethodDeposita() {
    	Conta conta = new Conta();
    	conta.saldo = 700;
    	conta.deposita(150.00);
    	assertEquals(850.00, conta.saldo); 
    	conta.deposita(123.56);
    	assertEquals(973.56, conta.saldo);
    }
} 

//+this.code+
class Conta {
	public String titular;
	public int numero;
	public String agencia; 
	public double saldo;
	public String data_abertura;
		
	double saca(double valor) {
		return saldo -= valor;
	}
	
	double deposita(double valor) {
		return saldo += valor;
	}
}
