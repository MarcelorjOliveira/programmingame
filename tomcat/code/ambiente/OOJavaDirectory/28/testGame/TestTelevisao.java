package testGame;

import org.junit.After;

import org.junit.AfterClass;

import org.junit.Before;

import org.junit.BeforeClass;

import org.junit.Test;

import static org.junit.Assert.*;

import br.com.programmingame.output.Televisao;

import br.com.programmingame.input.ControleRemoto;

public class TestTelevisao {

	@Test
	public void testCanal1() {
		Televisao televisao = new Televisao();

		televisao.setCanal(1);

		System.out.println("1");

		if (televisao.getCanal() == 1) {
			System.out.println(":true");
		} else {
			System.out.println(":false");
		}

		assertTrue(televisao.getCanal() == 1);
	}

	@Test
	public void testCanal2() {
		Televisao televisao = new Televisao();

		televisao.setCanal(10);

		System.out.println("10");

		if (televisao.getCanal() == 10) {
			System.out.println(":true");
		} else {
			System.out.println(":false");
		}

		assertTrue(televisao.getCanal() == 10);
	}

	@Test
	public void testAumentarCanal() {
		Televisao televisao = new Televisao();
		televisao.setCanal(2);

		ControleRemoto controleRemoto = new ControleRemoto();
		controleRemoto.setTelevisao(televisao);

		controleRemoto.aumentarCanal();

		System.out.println("aumentarCanal");

		if (televisao.getCanal() == 3) {
			System.out.println(":true");
		} else {
			System.out.println(":false");
		}

		assertTrue(televisao.getCanal() == 3);
	}

	@Test
	public void testDiminuirCanal() {
		Televisao televisao = new Televisao();
		televisao.setCanal(8);

		ControleRemoto controleRemoto = new ControleRemoto();
		controleRemoto.setTelevisao(televisao);

		controleRemoto.diminuirCanal();

		System.out.println("diminuirCanal");

		if (televisao.getCanal() == 7) {
			System.out.println(":true");
		} else {
			System.out.println(":false");
		}

		assertTrue(televisao.getCanal() == 7);
	}
}
