use halyeninstance_novo;

update Solution set test = "expected:<-5>" where idSolution = 1;

update Solution set test = "expected:<0>" where idSolution = 2;

update Solution set test = "expected:<87>" where idSolution = 3;

update Exercise set tests = "package Soma;

import org.junit.After;

import org.junit.AfterClass;

import org.junit.Before;

import org.junit.BeforeClass;

import org.junit.Test;

import static org.junit.Assert.*;

import Soma.Soma;


public class SomaTest {



    @Test

    public void testSoma1() {

	Soma soma = new Soma();

        assertEquals(-5, soma.soma(-2,-3));

    }



    @Test

    public void testSoma2() {

	Soma soma = new Soma();



       assertEquals(0, soma.soma(0,0));

    }



    @Test

    public void testSoma3() {

	Soma soma = new Soma();



 	assertEquals(87, soma.soma(43,44));

    }

}
" where id = 1;

update Exercise set tests = "package Fatorial;

import org.junit.After;

import org.junit.AfterClass;

import org.junit.Before;

import org.junit.BeforeClass;

import org.junit.Test;

import static org.junit.Assert.*;

import Fatorial.Fatorial;

public class FatorialTest {

    @Test

    public void testFatorial1() {

	Fatorial fatorial = new Fatorial();

	System.out.println(\"fatorial1\");
	if(fatorial.fatorial(1) == 1){
		System.out.println(\":true\");
	} else {
		System.out.println(\":false\");	
	}

        assertEquals(1, fatorial.fatorial(0));
    }



    @Test

    public void testFatorial2() {

	Fatorial fatorial = new Fatorial();

	System.out.println(\"fatorial2\");
	if(fatorial.fatorial(1) == 1){
		System.out.println(\":true\");
	} else {
		System.out.println(\":false\");	
	}

       assertEquals(1, fatorial.fatorial(1));

    }



    @Test

    public void testFatorial3() {

	Fatorial fatorial = new Fatorial();

	System.out.println(\"fatorial3\");
	if(fatorial.fatorial(5) == 120){
		System.out.println(\":true\");
	} else {
		System.out.println(\":false\");	
	}


 	assertEquals(120, fatorial.fatorial(5));

    }

    @Test

    public void testFatorial4() {

	Fatorial fatorial = new Fatorial();

	System.out.println(\"fatorial4\");
	if(fatorial.fatorial(8) == 40320){
		System.out.println(\":true\");
	} else {
		System.out.println(\":false\");	
	}


 	assertEquals(40320, fatorial.fatorial(8));

    }

    @Test

    public void testFatorial5() {

	Fatorial fatorial = new Fatorial();

	System.out.println(\"fatorial5\");
	if(fatorial.fatorial(11) == 39916800){
		System.out.println(\":true\");
	} else {
		System.out.println(\":false\");	
	}


 	assertEquals(39916800, fatorial.fatorial(11));

    }
}
"where id = 2;

update Solution set test = "fatorial1:false" where idSolution = 4;
update Solution set test = "fatorial2:false" where idSolution = 5;
update Solution set test = "fatorial3:false" where idSolution = 6;
update Solution set test = "fatorial4:false" where idSolution = 7;
update Solution set test = "fatorial5:false" where idSolution = 8;

update Exercise set tests = "package Fibonnaci;

import org.junit.After;

import org.junit.AfterClass;

import org.junit.Before;

import org.junit.BeforeClass;

import org.junit.Test;

import static org.junit.Assert.*;

import Fibonnaci.Fibonnaci;


public class FibonnaciTest {

    @Test

    public void testFibonnaci1() {

	Fibonnaci fibonnaci = new Fibonnaci();

	System.out.println(\"fibonnaci1\");

        if(fibonnaci.fibonnaci(1) == 1 )
        {System.out.println(\":true\");
	}
	else {
	System.out.println(\":false\");
	}

        assertEquals(1, fibonnaci.fibonnaci(1));

    }



    @Test

    public void testFibonnaci2() {

	Fibonnaci fibonnaci = new Fibonnaci();

	System.out.println(\"fibonnaci2\");

        if(fibonnaci.fibonnaci(2) == 1 )
        {System.out.println(\":true\");
	}
	else {
	System.out.println(\":false\");
	}


       assertEquals(1, fibonnaci.fibonnaci(2));

    }

    @Test

    public void testFibonnaci3() {

	Fibonnaci fibonnaci = new Fibonnaci();

	System.out.println(\"fibonnaci3\");

        if(fibonnaci.fibonnaci(5) == 5)
        {System.out.println(\":true\");
	}
	else {
	System.out.println(\":false\");
	}


 	assertEquals(5, fibonnaci.fibonnaci(5));

    }

    @Test
    public void testFibonnaci4() {

	Fibonnaci fibonnaci = new Fibonnaci();

	System.out.println(\"fibonnaci4\");

        if(fibonnaci.fibonnaci(7) == 13 )
        {System.out.println(\":true\");
	}
	else {
	System.out.println(\":false\");
	}


 	assertEquals(13, fibonnaci.fibonnaci(7));
    }

    @Test
    public void testFibonnaci5() {

	Fibonnaci fibonnaci = new Fibonnaci();

	System.out.println(\"fibonnaci5\");

        if(fibonnaci.fibonnaci(9) == 34)
        {System.out.println(\":true\");
	}
	else {
	System.out.println(\":false\");
	}

 	assertEquals(34, fibonnaci.fibonnaci(9));
    }
}" where id = 3;

update Solution set test = "fibonnaci1:false" where idSolution = 9;
update Solution set test = "fibonnaci2:false" where idSolution = 10;
update Solution set test = "fibonnaci3:false" where idSolution = 11;
update Solution set test = "fibonnaci4:false" where idSolution = 12;
update Solution set test = "fibonnaci5:false" where idSolution = 13;

update Exercise set tests = "package Produtorio;

import org.junit.After;

import org.junit.AfterClass;

import org.junit.Before;

import org.junit.BeforeClass;

import org.junit.Test;

import static org.junit.Assert.*;

import Produtorio.Produtorio;

public class ProdutorioTest {


    @Test

    public void testProdutorio1() {

	Produtorio produtorio = new Produtorio();

	System.out.println(\"produtorio1\");

        if(produtorio.produtorio(1,4) == 24 )
        {System.out.println(\":true\");
	}
	else {
	System.out.println(\":false\");
	}

        assertEquals(24, produtorio.produtorio(1,4));

    }

    @Test

    public void testProdutorio2() {

		Produtorio produtorio = new Produtorio();

		System.out.println(\"produtorio2\");

        if(produtorio.produtorio(3,7) == 2520 )
        {
			System.out.println(\":true\");
		}
		else {
			System.out.println(\":false\");
		}
       assertEquals(2520, produtorio.produtorio(3,7));

    }

    @Test

    public void testProdutorio3() {

	Produtorio produtorio = new Produtorio();

	System.out.println(\"produtorio3\");

        if(produtorio.produtorio(0,10) == 0 )
        {System.out.println(\":true\");
	}
	else {
		System.out.println(\":false\");
	}


 	assertEquals(0, produtorio.produtorio(0,10));

    }

    @Test

    public void testProdutorio4() {

	Produtorio produtorio = new Produtorio();

	System.out.println(\"produtorio4\");

        if(produtorio.produtorio(1,1) == 1 )
        {System.out.println(\":true\");
	}
	else {
	System.out.println(\":false\");
	}

 	assertEquals(1, produtorio.produtorio(1,1));

    }

    @Test

    public void testProdutorio5() {

	Produtorio produtorio = new Produtorio();

	System.out.println(\"produtorio5\");

        if(produtorio.produtorio(7,7) == 7 )
        {System.out.println(\":true\");
	}
	else {
	System.out.println(\":false\");
	}

 	assertEquals(7, produtorio.produtorio(7,7));

    }
}
" where id = 5;

update Solution set test = "produtorio1:false" where idSolution = 14;
update Solution set test = "produtorio2:false" where idSolution = 15;
update Solution set test = "produtorio3:false" where idSolution = 16;
update Solution set test = "produtorio4:false" where idSolution = 17;
update Solution set test = "produtorio5:false" where idSolution = 18;

update Exercise set tests = "package Multiplo;

import org.junit.After;

import org.junit.AfterClass;

import org.junit.Before;

import org.junit.BeforeClass;

import org.junit.Test;

import static org.junit.Assert.*;

import Multiplo.Multiplo;

public class MultiploTest {


    @Test

    public void testMultiplo1() {

	Multiplo multiplo = new Multiplo();

	System.out.println(\"multiplo1\");

        if(multiplo.multiplo(1,1) == 1 )
        {System.out.println(\":true\");
	}
	else {
	System.out.println(\":false\");
	}

        assertEquals(1, multiplo.multiplo(1,1));

    }



    @Test

    public void testMultiplo2() {

	Multiplo multiplo = new Multiplo();

	System.out.println(\"multiplo2\");

        if(multiplo.multiplo(1,100) == 0 )
        {System.out.println(\":true\");
	}
	else {
	System.out.println(\":false\");
	}


       assertEquals(0, multiplo.multiplo(1,100));

    }

    @Test

    public void testMultiplo3() {

	Multiplo multiplo = new Multiplo();

	System.out.println(\"multiplo3\");

        if(multiplo.multiplo(108,1) == 1 )
        {System.out.println(\":true\");
	}
	else {
	System.out.println(\":false\");
	}


 	assertEquals(1, multiplo.multiplo(108,1));

    }

    @Test

    public void testMultiplo4() {

	Multiplo multiplo = new Multiplo();

	System.out.println(\"multiplo4\");

        if(multiplo.multiplo(8,4) == 1 )
        {System.out.println(\":true\");
	}
	else {
	System.out.println(\":false\");
	}


 	assertEquals(1, multiplo.multiplo(8,4));

    }

    @Test
    public void testmultiplo5() {

	Multiplo multiplo = new Multiplo();

	System.out.println(\"multiplo5\");

        if(multiplo.multiplo(3,2) == 0 )
        {System.out.println(\":true\");
	}
	else {
	System.out.println(\":false\");
	}


 	assertEquals(0, multiplo.multiplo(3,2));

    }

    @Test
    public void testmultiplo6() {

	Multiplo multiplo = new Multiplo();

	System.out.println(\"multiplo6\");

        if(multiplo.multiplo(80,20) == 1 )
        {System.out.println(\":true\");
	}
	else {
	System.out.println(\":false\");
	}


 	assertEquals(1, multiplo.multiplo(80,20));

    }

}
" where id = 6; 

update Exercise set tests = "package Lanchonete;

import org.junit.After;

import org.junit.AfterClass;

import org.junit.Before;

import org.junit.BeforeClass;

import org.junit.Test;

import static org.junit.Assert.*;

import Lanchonete.Lanchonete;

public class LanchoneteTest {

    @Test

    public void testLanchonete1() {

	Lanchonete lanchonete = new Lanchonete();

	System.out.println(\"lanchonete1\");

        if(lanchonete.lanchonete(1,1) == 2.00 )
        {System.out.println(\":true\");
	}
	else {
	System.out.println(\":false\");
	}

        assertEquals(2.00, lanchonete.lanchonete(1,1), 0.0);

    }

    @Test

    public void testLanchonete2() {

	Lanchonete lanchonete = new Lanchonete();

	System.out.println(\"lanchonete2\");

        if(lanchonete.lanchonete(1,3) == 6.00 )
        {System.out.println(\":true\");
	}
	else {
	System.out.println(\":false\");
	}


       assertEquals(6.00, lanchonete.lanchonete(1,3), 0.0);

    }



    @Test

    public void testlanchonete3() {

	Lanchonete lanchonete = new Lanchonete();

	System.out.println(\"lanchonete3\");

        if(lanchonete.lanchonete(2,1) == 2.50 )
        {System.out.println(\":true\");
	}
	else {
	System.out.println(\":false\");
	}


 	assertEquals(2.50, lanchonete.lanchonete(2,1), 0.0);

    }

    @Test

    public void testlanchonete4() {

	Lanchonete lanchonete = new Lanchonete();

	System.out.println(\"lanchonete4\");

        if(lanchonete.lanchonete(2,4) == 10.00 )
        {System.out.println(\":true\");
	}
	else {
	System.out.println(\":false\");
	}


 	assertEquals(10.00, lanchonete.lanchonete(2,4), 0.0);

    }

    @Test

    public void testlanchonete5() {

	Lanchonete lanchonete = new Lanchonete();

	System.out.println(\"lanchonete5\");

        if(lanchonete.lanchonete(3,1) == 5.00 )
        {System.out.println(\":true\");
	}
	else {
	System.out.println(\":false\");
	}


 	assertEquals(5.00, lanchonete.lanchonete(3,1));

    }

    @Test

    public void testLanchonete6() {

	Lanchonete lanchonete = new Lanchonete();

	System.out.println(\"lanchonete6\");

        if(lanchonete.lanchonete(3,5) == 25.0 )
        {System.out.println(\":true\");
	}
	else {
	System.out.println(\":false\");
	}


        assertEquals(25.00, lanchonete.lanchonete(3,5), 0.0);

    }



    @Test

    public void testLanchonete7() {

	Lanchonete lanchonete = new Lanchonete();

	System.out.println(\"lanchonete7\");

        if(lanchonete.lanchonete(4,1) == 3.50 )
        {System.out.println(\":true\");
	}
	else {
	System.out.println(\":false\");
	}


       assertEquals(3.50, lanchonete.lanchonete(4,1),0.0);

    }



    @Test

    public void testlanchonete8() {

	Lanchonete lanchonete = new Lanchonete();

	System.out.println(\"lanchonete8\");

        if(lanchonete.lanchonete(4,2) == 7.00 )
        {System.out.println(\":true\");
	}
	else {
	System.out.println(\":false\");
	}


 	assertEquals(7.00, lanchonete.lanchonete(4,2), 0.0);

    }

    @Test

    public void testlanchonete9() {

	Lanchonete lanchonete = new Lanchonete();

	System.out.println(\"lanchonete9\");

        if(lanchonete.lanchonete(5,1) == 8.50 )
        {System.out.println(\":true\");
	}
	else {
	System.out.println(\":false\");
	}


 	assertEquals(8.50, lanchonete.lanchonete(5,1));

    }

    @Test

    public void testlanchonete10() {

	Lanchonete lanchonete = new Lanchonete();

	System.out.println(\"lanchonete10\");

        if(lanchonete.lanchonete(5,11) == 93.50 )
        {System.out.println(\":true\");
	}
	else {
	System.out.println(\":false\");
	}

 	assertEquals(93.50, lanchonete.lanchonete(5,11));

    }



}" where id = 8;

update Solution set test = "multiplo1:false" where idSolution = 19;
update Solution set test = "multiplo2:false" where idSolution = 20;
update Solution set test = "multiplo3:false" where idSolution = 21;
update Solution set test = "multiplo4:false" where idSolution = 22;
update Solution set test = "multiplo5:false" where idSolution = 23;
update Solution set test = "multiplo6:false" where idSolution = 24;

update Solution set test = "somatorio1:false" where idSolution = 25;
update Solution set test = "somatorio2:false" where idSolution = 25;
update Solution set test = "somatorio3:false" where idSolution = 26;
update Solution set test = "somatorio4:false" where idSolution = 27;
update Solution set test = "somatorio5:false" where idSolution = 28;



update Solution set test = "lanchonete1:false" where idSolution = 29;
update Solution set test = "lanchonete2:false" where idSolution = 30;
update Solution set test = "lanchonete3:false" where idSolution = 31;
update Solution set test = "lanchonete4:false" where idSolution = 32;
update Solution set test = "lanchonete5:false" where idSolution = 33;
update Solution set test = "lanchonete6:false" where idSolution = 34;
update Solution set test = "lanchonete7:false" where idSolution = 35;
update Solution set test = "lanchonete8:false" where idSolution = 36;
update Solution set test = "lanchonete9:false" where idSolution = 37;
update Solution set test = "lanchonete10:false" where idSolution = 38;

select * from Exercise;
select * from Solution;
