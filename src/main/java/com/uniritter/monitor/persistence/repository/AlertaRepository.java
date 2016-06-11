package com.uniritter.monitor.persistence.repository;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.repository.IAlertaRepository;
import com.uniritter.monitor.domain.repository.model.AlertaEventData;
import com.uniritter.monitor.domain.repository.model.GenericEventData;
import com.uniritter.monitor.persistence.model.AlertaEntity;
import com.uniritter.monitor.persistence.service.GenericDao;

@Component
public class AlertaRepository implements IAlertaRepository {

	private GenericDao dao;

	private final JdbcTemplate jdbcTemplate;
		
	@Autowired
	public AlertaRepository(JdbcTemplate jdbcTemplate) {
		
		this.jdbcTemplate = jdbcTemplate;
		this.dao = new GenericDao(this.jdbcTemplate, "alerta", "metricaid");
	}

	@Override
	public List<? extends GenericEventData> getList() {
		List<AlertaEntity> alertaEntity = this.dao.<AlertaEntity>getList(AlertaEntity.class);

		ModelMapper modelMapper = new ModelMapper();
		Type listType = new TypeToken<List<AlertaEventData>>() {
		}.getType();
		List<AlertaEventData> alertas = modelMapper.map(alertaEntity, listType);

		return alertas;
	}

	@Override
	public List<? extends GenericEventData> getListFromRelation(int relatedId) {
		List<AlertaEntity> alertaEntity = this.dao.<AlertaEntity>getFromParent(AlertaEntity.class, relatedId);

		ModelMapper modelMapper = new ModelMapper();
		Type listType = new TypeToken<List<AlertaEventData>>() {
		}.getType();
		List<AlertaEventData> alertas = modelMapper.map(alertaEntity, listType);

		return alertas;
	}

	@Override
	public int save(GenericEventData entidade) {

		ModelMapper modelMapper = new ModelMapper();
		AlertaEntity alertaEntity = modelMapper.map(entidade, AlertaEntity.class);

		return this.dao.create(alertaEntity);
	}

	@Override
	public int saveToRelation(GenericEventData entidade, int relatedId) {

		ModelMapper modelMapper = new ModelMapper();
		AlertaEntity alertaEntity = modelMapper.map(entidade, AlertaEntity.class);

		alertaEntity.setMetricaId(relatedId);

		return this.dao.create(alertaEntity);
	}

	@Override
	public GenericEventData getById(int id) {

		AlertaEntity alertaEntity = this.dao.<AlertaEntity>getById(AlertaEntity.class, id);

		ModelMapper modelMapper = new ModelMapper();
		GenericEventData entidade = modelMapper.map(alertaEntity, GenericEventData.class);

		return entidade;
	}

	@Override
	public int deleteById(int id) {

		return this.dao.delete(id);
	}

	@Override
	public int deleteFromRelation(int relatedId) {
		return this.dao.deleteFromParent(relatedId);
	}
}
