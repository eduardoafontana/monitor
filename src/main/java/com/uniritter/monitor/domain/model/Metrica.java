package com.uniritter.monitor.domain.model;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.repository.MetricaEventData;
import com.uniritter.monitor.domain.service.AlertaService;
import com.uniritter.monitor.domain.service.HostService;
import com.uniritter.monitor.domain.service.MetricaService;

@Component
public class Metrica {

	private int id;
	private MetricaTipo tipo;
	private Date created;

	private List<Host> hosts;
	private List<Alerta> alertas;
	// private List<Medicao> medicoes;

	private MetricaService metricaService;
	private HostService hostService;
	private AlertaService alertaService;

	@Autowired
	public Metrica(MetricaService metricaService, HostService hostService, AlertaService alertaService) {

		this.metricaService = metricaService;
		this.hostService = hostService;
		this.alertaService = alertaService;
	}

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

	public final List<Host> getHosts() {
		if (this.hosts == null)
			this.hosts = hostService.retrieveAll(this.id);

		// Verificar se hosts do lado de fora de Metrica pode ser foi add

		return hosts;
	}

	public final List<Alerta> getAlertas() {
		if (this.alertas == null)
			this.alertas = alertaService.retrieveAll(this.id);

		// Verificar se alerta do lado de fora de Metrica pode ser foi add

		return alertas;
	}

	public int save() {
		// testar se metrica eh valida

		ModelMapper modelMapper = new ModelMapper();
		MetricaEventData metricaData = modelMapper.map(this, MetricaEventData.class);

		this.id = metricaService.persist(metricaData);

		if (this.hosts != null) {
			for (Host host : this.hosts) {
				host.save(this.id);
			}
		}

		if (this.alertas != null) {
			for (Alerta alerta : this.alertas) {
				alerta.save(this.id);
			}
		}

		return this.id;
	}

	// private void adicionarMedicao(Medicao medicao) {
	// medicoes.add(medicao);
	// }
	//
	// public List<Medicao> historicoMedicoes() {
	// return medicoes;
	// }
	//
	// public Medicao getUltimaMedicao() {
	// if (medicoes.size() > 0)
	// return medicoes.get(medicoes.size() - 1);
	//
	// return null;
	// }
	//
	// public boolean novaMedicao(int valor) {
	// Medicao medicao = new Medicao(valor);
	// adicionarMedicao(medicao);
	//
	// setChanged();
	// notifyObservers(getUltimaMedicao());
	//
	// return true;
	// }
}
