package com.uniritter.monitor.domain.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.model.*;

@Component
public class MetricaService {

	@Autowired
	MetricaRepository repository;

	public List<Metrica> getMetricas() {
		return repository.getMetricas();
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

		Host host = new Host(HostGrupo.valueOf(metricaDados.Grupo));
		host.IP = metricaDados.IP;
		host.MAC = metricaDados.MAC;

		return repository.createMetrica(new Metrica(1, "nova metrica", new Date()));
	}	
}
