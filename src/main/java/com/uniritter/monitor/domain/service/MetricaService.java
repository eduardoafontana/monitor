package com.uniritter.monitor.domain.service;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.model.*;
import com.uniritter.monitor.domain.repository.IEntity;
import com.uniritter.monitor.domain.repository.IHostRepository;
import com.uniritter.monitor.domain.repository.IMetricaRepository;

@Component
public class MetricaService {

	private final IMetricaRepository metricaRepository;
	private final HostService hostService;
	
	@Autowired
	public MetricaService(IMetricaRepository metricaRepository, HostService hostService){
		this.metricaRepository = metricaRepository;
		this.hostService = hostService;
	}
	
	@SuppressWarnings("unchecked")
	public List<Metrica> getMetricas() {
		
		List<Metrica> metricas = (List<Metrica>)metricaRepository.getList();
		
		for(Metrica metrica : metricas){
			metrica.hosts = hostService.getHosts(metrica.id);
		}
		
		return metricas;
	}
	
	public Metrica getMetrica(int id) {
		IEntity metrica = metricaRepository.getById(id);
		
		if(metrica == null)
			return null;
		
		Metrica metricaConvertida = (Metrica)metrica;
		metricaConvertida.hosts = hostService.getHosts(metricaConvertida.id);
		
		return metricaConvertida;
	}
	
	public int deleteMetrica(int id) {
		return metricaRepository.deleteById(id);
	}
	
	public String[] getTipos() {
		return Arrays.toString(MetricaTipo.values()).split(", ");
	}
	
	public int createMetrica(MetricaTipo metricaTipo) {
		
		Metrica metrica = new Metrica();
		metrica.setTipo(metricaTipo);
		
		return metricaRepository.save(metrica);
	}
}
