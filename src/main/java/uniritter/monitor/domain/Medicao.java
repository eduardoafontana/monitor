package uniritter.monitor.domain;

public class Medicao {
	public int guid;
	private int valor;
	
	public int getValor() {
		return this.valor;
	}

	public Medicao(int valor){
		guid = 666;
		this.valor = valor;
	}  
}
