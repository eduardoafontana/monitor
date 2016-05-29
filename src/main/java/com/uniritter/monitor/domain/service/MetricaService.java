package com.uniritter.monitor.domain.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.model.*;
import com.uniritter.monitor.domain.repository.IMetricaRepository;

@Component
public class MetricaService {

	private final IMetricaRepository repository;
	
	@Autowired
	public MetricaService(IMetricaRepository repository){
		this.repository = repository;
	}	
	
	public List<Metrica> getMetricas() {
		return (List<Metrica>)repository.getList();
	}

//	public Metrica createMetrica(MetricaDados metricaDados) {
//
//		Host host = new Host(HostGrupo.valueOf(metricaDados.Grupo));
//		host.IP = metricaDados.IP;
//		host.MAC = metricaDados.MAC;
//		
//		return new Metrica(MetricaTipo.valueOf(metricaDados.Tipo), host);
//		
//		// TODO adicionar ao repositorio
//		// return repository.createMetrica(nomeMetrica);
//	}
	
	public int createMetrica(MetricaDados metricaDados) {

//		Host host = new Host(HostGrupo.valueOf(metricaDados.Grupo));
//		host.IP = metricaDados.IP;
//		host.MAC = metricaDados.MAC;

		ModelMapper modelMapper = new ModelMapper();
		Metrica metrica = modelMapper.map(metricaDados, Metrica.class);
		
		return repository.save(metrica);
	}
}
