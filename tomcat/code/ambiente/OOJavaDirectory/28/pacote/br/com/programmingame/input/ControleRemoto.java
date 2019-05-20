package br.com.programmingame.input;

import br.com.programmingame.output.Televisao;

public class ControleRemoto {
	private Televisao televisao;
	
	public void setTelevisao(Televisao televisao) {
		this.televisao = televisao;
	}
	
	public void aumentarCanal() {
		televisao.setCanal(televisao.getCanal() + 1);
	}
	
	public void diminuirCanal() {
		televisao.setCanal(televisao.getCanal() - 1);
	}

}
