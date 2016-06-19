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

	public List<Metrica> getTodos() {

		List<MetricaEventData> metricaEventData = metricaRepository.<MetricaEventData>getList();

		List<Metrica> metricas = new ArrayList<Metrica>();

		for (MetricaEventData metricaData : metricaEventData) {

			Metrica metrica = new Metrica(this, hostService, alertaService, medicaoService);
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.map(metricaData, metrica);

			metricas.add(metrica);
		}

		return metricas;
	}

	public Metrica getUnico(int id) {
		MetricaEventData metricaEventData = metricaRepository.<MetricaEventData>getById(id);

		if (metricaEventData == null)
			return null;

		Metrica metrica = new Metrica(this, hostService, alertaService, medicaoService);
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(metricaEventData, metrica);

		return metrica;
	}

	public int apagar(int id) {

		int rowsAlertas = alertaService.apagarPorMetrica(id);

		int rowsHosts = hostService.gravarPorMetrica(id);
		
		int rowsMedicoes = medicaoService.apagarPorMetrica(id);

		return rowsAlertas + rowsHosts + rowsMedicoes + metricaRepository.deleteById(id);
	}

	public int criar(MetricaTipo metricaTipo) {

		Metrica metrica = new Metrica(this, hostService, alertaService, medicaoService);
		metrica.setTipo(metricaTipo);

		return metrica.save();
	}

	public int gravar(MetricaEventData metricaData) {
		return metricaRepository.save(metricaData);
	}
	
	public List<Medicao> getHistoricoMedicoes(int metricaId) {

		Metrica metrica = this.getUnico(metricaId);

		if(metrica == null)
			return null;
		
		return metrica.getHistoricoMedicoes();
	}
	
	public Medicao getUltimaMedicao(int metricaId) {

		Metrica metrica = this.getUnico(metricaId);

		if(metrica == null)
			return null;
		
		return metrica.getUltimaMedicao();
	}
}
