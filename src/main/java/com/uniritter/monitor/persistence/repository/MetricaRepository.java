package com.uniritter.monitor.persistence.repository;


import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.model.Metrica;
import com.uniritter.monitor.domain.repository.IEntity;
import com.uniritter.monitor.domain.repository.IRepository;
import com.uniritter.monitor.persistence.model.MetricaEntity;
import com.uniritter.monitor.persistence.service.MetricaDao;

@Component
public class MetricaRepository implements IRepository {
	
	private final MetricaDao metricaDao;
	
	@Autowired
	public MetricaRepository(MetricaDao metricaDao){
		this.metricaDao = metricaDao;
	}
	
	@Override
	public List<? extends IEntity> getList() {
		List<MetricaEntity> metricaEntity = this.metricaDao.getMetricas();
		
		ModelMapper modelMapper = new ModelMapper();    
		Type listType = new TypeToken<List<Metrica>>() {}.getType();
		List<Metrica> metricas = modelMapper.map(metricaEntity, listType);	
		
		return metricas;
	}

	@Override
	public int save(IEntity entidade) {
		Metrica metrica = (Metrica)entidade; 
		
		ModelMapper modelMapper = new ModelMapper();
		MetricaEntity metricaEntity = modelMapper.map(metrica, MetricaEntity.class);
		
		return this.metricaDao.createMetrica(metricaEntity);
	}
}