package com.uniritter.monitor.domain.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.model.*;
import com.uniritter.monitor.domain.repository.IMetricaRepository;
import com.uniritter.monitor.domain.repository.model.MetricaEventData;

@Component
public class MetricaService {

	private final IMetricaRepository metricaRepository;
	private final HostService hostService;
	private final AlertaService alertaService;
	private final MedicaoService medicaoService;

	@Autowired
	public MetricaService(IMetricaRepository metricaRepository, HostService hostService, AlertaService alertaService, MedicaoService medicaoService) {
		this.metricaRepository = metricaRepository;
		this.hostService = hostService;
		this.alertaService = alertaService;
		this.medicaoService = medicaoService;
	}

	public String[] retrieveTipos() {
		return Arrays.toString(MetricaTipo.values()).split(", ");
	}

	@SuppressWarnings("unchecked")
	public List<Metrica> retrieveAll() {

		List<MetricaEventData> metricaEventData = (List<MetricaEventData>) metricaRepository.getList();

		List<Metrica> metricas = new ArrayList<Metrica>();

		for (MetricaEventData metricaData : metricaEventData) {

			Metrica metrica = new Metrica(this, hostService, alertaService, medicaoService);
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.map(metricaData, metrica);

			metricas.add(metrica);
		}

		return metricas;
	}

	public Metrica retrieve(int id) {
		MetricaEventData metricaEventData = (MetricaEventData) metricaRepository.getById(id);

		if (metricaEventData == null)
			return null;

		Metrica metrica = new Metrica(this, hostService, alertaService, medicaoService);
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(metricaEventData, metrica);

		return metrica;
	}

	public int remove(int id) {

		int rowsAlertas = alertaService.removePorMetrica(id);

		int rowsHosts = hostService.removePorMetrica(id);
		
		int rowsMedicoes = medicaoService.removePorMetrica(id);

		return rowsAlertas + rowsHosts + rowsMedicoes + metricaRepository.deleteById(id);
	}

	public int create(MetricaTipo metricaTipo) {

		Metrica metrica = new Metrica(this, hostService, alertaService, medicaoService);
		metrica.setTipo(metricaTipo);

		return metrica.save();
	}

	public int persist(MetricaEventData metricaData) {
		return metricaRepository.save(metricaData);
	}
	
	public List<Medicao> getHistoricoMedicoes(int metricaId) {

		Metrica metrica = this.retrieve(metricaId);

		if(metrica == null)
			return null;
		
		return metrica.getHistoricoMedicoes();
	}
	
	public Medicao getUltimaMedicao(int metricaId) {

		Metrica metrica = this.retrieve(metricaId);

		if(metrica == null)
			return null;
		
		return metrica.getUltimaMedicao();
	}
}
