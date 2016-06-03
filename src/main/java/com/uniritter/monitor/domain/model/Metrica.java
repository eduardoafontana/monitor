package com.uniritter.monitor.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

import org.springframework.beans.factory.annotation.Autowired;

import com.uniritter.monitor.domain.repository.IEntity;
import com.uniritter.monitor.domain.repository.IHostRepository;
import com.uniritter.monitor.domain.service.HostService;

public class Metrica extends Observable implements IEntity {
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MetricaTipo getTipo() {
		return this.tipo;
	}

	public void setTipo(MetricaTipo tipo) {
		this.tipo = tipo;
	}	
	
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	public List<Host> getHosts() {
		return hosts;
	}

	public void setHosts(List<Host> hosts) {
		this.hosts = hosts;
	}

	public int id;
	private MetricaTipo tipo;
	public Date created;
	
	public List<Host> hosts;
//	private List<Medicao> medicoes;
//	private List<Alerta> alertas;
	
//	@Autowired
//	HostService hostService;
	
//	private final HostService hostService;
//	
//	@Autowired
//	public Metrica(HostService hostService){
//		this.hostService = hostService;
//	}

//	public Metrica(MetricaTipo tipo, Host host) {
//		this.tipo = tipo;
//		this.host = host;
//		// Pediodicidade
//
//		medicoes = new ArrayList<Medicao>();
//		alertas = new ArrayList<Alerta>();
//	}
//	
//	public Metrica(int id, String nome, Date created) {
//		this.id = id;
//		this.created = created;
//	
//		//Foi inserido apenas para funcionar em aula.
//		this.tipo = MetricaTipo.CargaDeRede;
//		this.host = new Host(HostGrupo.Firewall);
//		
//		medicoes = new ArrayList<Medicao>();
//		alertas = new ArrayList<Alerta>();
//		
//	}	
//
//	private void adicionarMedicao(Medicao medicao) {
//		medicoes.add(medicao);
//	}
//
//	public List<Medicao> historicoMedicoes() {
//		return medicoes;
//	}
//
//	public Medicao getUltimaMedicao() {
//		if (medicoes.size() > 0)
//			return medicoes.get(medicoes.size() - 1);
//		
//		return null;
//	}
//
//	public boolean novaMedicao(int valor) {
//		Medicao medicao = new Medicao(valor);
//		adicionarMedicao(medicao);
//
//		setChanged();
//		notifyObservers(getUltimaMedicao());
//
//		return true;
//	}
}
