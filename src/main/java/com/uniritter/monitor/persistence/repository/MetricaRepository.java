package com.uniritter.monitor.persistence.repository;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.repository.IMetricaRepository;
import com.uniritter.monitor.domain.repository.model.GenericEventData;
import com.uniritter.monitor.domain.repository.model.MetricaEventData;
import com.uniritter.monitor.persistence.model.MetricaEntity;
import com.uniritter.monitor.persistence.service.MetricaDao;

@Component
public class MetricaRepository implements IMetricaRepository {

	private final MetricaDao metricaDao;

	@Autowired
	public MetricaRepository(MetricaDao metricaDao) {
		this.metricaDao = metricaDao;
	}

	@Override
	public List<? extends GenericEventData> getList() {
		List<MetricaEntity> metricaEntity = this.metricaDao.getMetricas();

		ModelMapper modelMapper = new ModelMapper();
		Type listType = new TypeToken<List<MetricaEventData>>() {
		}.getType();
		List<MetricaEventData> metricas = modelMapper.map(metricaEntity, listType);

		return metricas;
	}

	// @Override
	// public List<? extends IEntity> getListFromParent(int parentId) throws
	// HasNoParentException {
	// throw new HasNoParentException("Metrica nao eg relacionada a alguem.");
	// }

	// TODO, implementar acima, lancando excecao customizada.
	@Override
	public List<? extends GenericEventData> getListFromRelation(int relatedId) {
		return new ArrayList<GenericEventData>();
	}

	@Override
	public int save(GenericEventData entidade) {

		ModelMapper modelMapper = new ModelMapper();
		MetricaEntity metricaEntity = modelMapper.map(entidade, MetricaEntity.class);

		return this.metricaDao.createMetrica(metricaEntity);
	}

	@Override
	public GenericEventData getById(int id) {

		MetricaEntity metricaEntity = this.metricaDao.getMetrica(id);

		if (metricaEntity == null)
			return null;

		ModelMapper modelMapper = new ModelMapper();
		MetricaEventData entidade = modelMapper.map(metricaEntity, MetricaEventData.class);

		return entidade;
	}

	@Override
	public int deleteById(int id) {

		return this.metricaDao.deleteMetrica(id);
	}

	@Override
	public int saveToRelation(GenericEventData entidade, int relatedId) {

		// lancar execao de objeto nao eh relacionada a alguém

		return 0;
	}

	@Override
	public int deleteFromRelation(int relatedId) {
		// lancar execao de objeto nao eh relacionada a alguém

		return 0;
	}
}
