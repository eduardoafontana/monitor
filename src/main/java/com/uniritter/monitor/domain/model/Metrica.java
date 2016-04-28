package com.uniritter.monitor.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Metrica extends Observable {
	private MetricaTipo tipo;
	private Host host;
	private List<Medicao> medicoes;
	private List<Alerta> alertas;

	public MetricaTipo getTipo() {
		return this.tipo;
	}

	public Host getHost() {
		return this.host;
	}

	public Metrica(MetricaTipo tipo, Host host) {
		this.tipo = tipo;
		this.host = host;
		// Pediodicidade

		medicoes = new ArrayList<Medicao>();
		alertas = new ArrayList<Alerta>();
	}

	private void adicionarMedicao(Medicao medicao) {
		medicoes.add(medicao);
	}

	public List<Medicao> historicoMedicoes() {
		return medicoes;
	}

	public Medicao getUltimaMedicao() {
		if (medicoes.size() > 0)
			return medicoes.get(medicoes.size() - 1);
		
		return null;
	}

	public boolean novaMedicao(int valor) {
		Medicao medicao = new Medicao(valor);
		adicionarMedicao(medicao);

		setChanged();
		notifyObservers(getUltimaMedicao());

		return true;
	}
}
