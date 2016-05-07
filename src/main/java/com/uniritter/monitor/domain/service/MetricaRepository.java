package com.uniritter.monitor.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.model.Metrica;
import com.uniritter.monitor.persistence.MetricaDao;

@Component
public class MetricaRepository {
	
	@Autowired
	MetricaDao metricaDao;
	
	public List<Metrica> getMetricas() {
		
		//List<Metrica> metricas = new ArrayList<Metrica>();
		
		//metricas.add(arg0);
		
		//aqui no repositorio deve enviar um objeto de dados de consulta, por exemplo, e definir a interface esperada do objeto de dados de retorno que será construída pelo dao. 		
		return this.metricaDao.getMetricas();
	}
	
	public int createMetrica(Metrica metrica){
		return this.metricaDao.createMetrica(metrica);
	}
}
