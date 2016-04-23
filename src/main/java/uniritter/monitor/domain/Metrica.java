package uniritter.monitor.domain;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Metrica extends Observable {
	private Tipo tipo;
	private Host host;
	private List<Medicao> medicoes;
	
	public Tipo getTipo(){
		return this.tipo;
	}
	
	public Host getHost(){
		return this.host;
	}	
	
	public Metrica(Tipo tipo, Host host){
		this.tipo = tipo;
		this.host = host;
	}
	
	public Metrica(){
		
	}

	private void adicionarMedicao(Medicao medicao) {
		if (medicoes == null)
			medicoes = new ArrayList<Medicao>();
		
		medicoes.add(medicao);
	}

	public List<Medicao> historicoMedicoes() {
		return medicoes;
	}

	public Medicao getUltimaMedicao() {
		return medicoes.get(medicoes.size() - 1);
	}
	
	public boolean novaMedicao(int valor){
		Medicao medicao = new Medicao(valor);
		adicionarMedicao(medicao);
		
		setChanged();
		notifyObservers(getUltimaMedicao());
		
		return true;
	}
}
