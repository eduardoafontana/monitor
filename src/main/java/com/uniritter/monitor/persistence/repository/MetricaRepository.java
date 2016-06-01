package com.uniritter.monitor.persistence.repository;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.model.Metrica;
import com.uniritter.monitor.domain.repository.IEntity;
import com.uniritter.monitor.domain.repository.IMetricaRepository;
import com.uniritter.monitor.persistence.model.HostEntity;
import com.uniritter.monitor.persistence.model.MetricaEntity;
import com.uniritter.monitor.persistence.service.MetricaDao;
import com.uniritter.monitor.persistence.service.NoRowsAffected;

@Component
public class MetricaRepository implements IMetricaRepository {
	
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
	
//	@Override
//	public List<? extends IEntity> getListFromParent(int parentId) throws HasNoParentException {
//		throw new HasNoParentException("Metrica não é relacionada a alguém.");
//	}
	
	//TODO,  implementar acima, lancando exceção customizada.
	@Override
	public List<? extends IEntity> getListFromParent(int parentId){
		return new ArrayList<IEntity>();
	}

	@Override
	public int save(IEntity entidade) {
		//Metrica metrica = (Metrica)entidade; 
		
		ModelMapper modelMapper = new ModelMapper();
		MetricaEntity metricaEntity = modelMapper.map(entidade, MetricaEntity.class);
		
		return this.metricaDao.createMetrica(metricaEntity);
	}
	
	@Override
	public IEntity getById(int id) {

		MetricaEntity metricaEntity = this.metricaDao.getMetrica(id);
		
		if(metricaEntity == null)
			return null;
		
		ModelMapper modelMapper = new ModelMapper();
		Metrica entidade = modelMapper.map(metricaEntity, Metrica.class);
		
		return entidade;
	}
	
	@Override
	public int deleteById(int id) {

		return this.metricaDao.deleteMetrica(id);
	}
}
