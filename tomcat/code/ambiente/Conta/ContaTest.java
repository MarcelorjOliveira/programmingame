package Conta;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import Conta.Conta;

public class ContaTest {

    @Test
    public void testTitularAttribute() {
	Conta conta = new Conta();

	conta.titular = "Thiago";
        assertEquals("Thiago", conta.titular, 0.0);
    }

    @Test
    public void testSaldoAttribute() {
	Conta conta = new Conta();

	conta.saldo = 230.00;
        assertEquals(230.00, conta.saldo, 0.0);
    }

    @Test
    public void testMethodSaca() {
	Conta conta = new Conta();

	conta.saldo = 200.00;

	conta.saca(20);
	
	assertEquals(180.00, conta.saldo, 0.0);
    }
   
    @Test
    public void testMethodDeposita() {
	Conta conta = new Conta();

	conta.saldo = 100.00;
	conta.deposita(40);
        assertEquals(140.00, conta.saldo, 0.0);
    }
}
