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
	private final IHostRepository hostRepository;
	
	@Autowired
	public MetricaService(IMetricaRepository metricaRepository, IHostRepository hostRepository){
		this.metricaRepository = metricaRepository;
		this.hostRepository = hostRepository;
	}
	
	public List<Metrica> getMetricas() {
		return (List<Metrica>)metricaRepository.getList();
	}
	
	public Metrica getMetrica(int id) {
		IEntity metrica = metricaRepository.getById(id);
		
		if(metrica == null)
			return null;
		
		return (Metrica)metrica;
	}
	
	public int deleteMetrica(int id) {
		return metricaRepository.deleteById(id);
	}
	
	public String[] getTipos() {
		return Arrays.toString(MetricaTipo.values()).split(", ");
	}
	
	public int createMetrica(MetricaTipo metricaTipo) {
		
		Metrica metrica = new Metrica(metricaTipo);
		
		return metricaRepository.save(metrica);
	}
	
	public int addHost(long idMetrica, HostData hostData) {

		ModelMapper modelMapper = new ModelMapper();
		Host host = modelMapper.map(hostData, Host.class);
		
		host.metricaId = idMetrica;
		
		return hostRepository.save(host);
	}
}
