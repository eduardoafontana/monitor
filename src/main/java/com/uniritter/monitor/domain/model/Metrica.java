package com.uniritter.monitor.domain.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.repository.model.MetricaEventData;
import com.uniritter.monitor.domain.service.AlertaService;
import com.uniritter.monitor.domain.service.HostService;
import com.uniritter.monitor.domain.service.MedicaoService;
import com.uniritter.monitor.domain.service.MetricaService;

@Component
public class Metrica extends ControlData {

	private MetricaTipo tipo;

	private List<Host> hosts;
	private List<Alerta> alertas;
	private List<Medicao> medicoes;

	private MetricaService metricaService;
	private HostService hostService;
	private AlertaService alertaService;
	private MedicaoService medicaoService;

	@Autowired
	public Metrica(MetricaService metricaService, HostService hostService, AlertaService alertaService,
			MedicaoService medicaoService) {

		this.metricaService = metricaService;
		this.hostService = hostService;
		this.alertaService = alertaService;
		this.medicaoService = medicaoService;
	}

	public MetricaTipo getTipo() {
		return this.tipo;
	}

	public void setTipo(MetricaTipo tipo) {
		this.tipo = tipo;
	}

	public final List<Host> getHosts() {
		if (this.hosts == null)
			this.hosts = hostService.retrieveAll(this.id);

		//TODO: Verificar se hosts do lado de fora de Metrica pode ser foi add

		return hosts;
	}

	public final List<Alerta> getAlertas() {
		if (this.alertas == null)
			this.alertas = alertaService.retrieveAll(this.id);

		//TODO: Verificar se alerta do lado de fora de Metrica pode ser foi add

		return alertas;
	}

	public final List<Medicao> getMedicoes() {
		if (this.medicoes == null)
			this.medicoes = medicaoService.retrieveAll(this.id);

		//TODO: Verificar se medicao do lado de fora de Metrica pode ser foi add

		return medicoes;
	}

	public int save() {

		//TODO: local para aplicar regras de negocio antes de salvar. Ex: validacao

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

		if (this.medicoes != null) {
			for (Medicao medicao : this.medicoes) {
				medicao.save(this.id);
			}
		}

		return this.id;
	}

	public List<Medicao> getHistoricoMedicoes() {
		List<Medicao> medicoes = this.getMedicoes();

		Collections.sort(medicoes, new Comparator<Medicao>() {
			@Override
			public int compare(Medicao o1, Medicao o2) {
				return o1.getQuando().compareTo(o2.getQuando());
			}
		});

		//TODO: Aplicar lambda, porem so java 8
		//http://stackoverflow.com/questions/2784514/sort-arraylist-of-custom-objects-by-property
		
		return medicoes;
	}

	 public Medicao getUltimaMedicao() {
		 List<Medicao> historico = this.getHistoricoMedicoes();
		 
		 if(historico.size() > 0)
			 return historico.get(historico.size() - 1);
		 
		 return null;
	 }

	public void verificarAlertasNotificar() {
		
		Medicao ultimaMedicao = this.getUltimaMedicao();
		List<Alerta> alertas = this.getAlertas();
		
		for (Alerta alerta : alertas) {
			if(alerta.RegraVerdadeira(ultimaMedicao.getValor()))
				alerta.Notificar();
		}
	}
}
