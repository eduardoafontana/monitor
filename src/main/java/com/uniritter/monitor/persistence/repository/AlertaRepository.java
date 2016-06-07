package com.uniritter.monitor.persistence.repository;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.model.Alerta;
import com.uniritter.monitor.domain.repository.AlertaEventData;
import com.uniritter.monitor.domain.repository.IEntity;
import com.uniritter.monitor.domain.repository.IAlertaRepository;
import com.uniritter.monitor.persistence.model.AlertaEntity;
import com.uniritter.monitor.persistence.service.AlertaDao;

@Component
public class AlertaRepository implements IAlertaRepository {

	private final AlertaDao alertaDao;

	@Autowired
	public AlertaRepository(AlertaDao alertaDao) {
		this.alertaDao = alertaDao;
	}

	@Override
	public List<? extends IEntity> getList() {
		List<AlertaEntity> alertaEntity = this.alertaDao.getAlertas();

		ModelMapper modelMapper = new ModelMapper();
		Type listType = new TypeToken<List<Alerta>>() {
		}.getType();
		List<AlertaEventData> alertas = modelMapper.map(alertaEntity, listType);

		return alertas;
	}

	@Override
	public List<? extends IEntity> getListFromRelation(int relatedId) {
		List<AlertaEntity> alertaEntity = this.alertaDao.getAlertasFromParent(relatedId);

		ModelMapper modelMapper = new ModelMapper();
		Type listType = new TypeToken<List<AlertaEventData>>() {
		}.getType();
		List<AlertaEventData> alertas = modelMapper.map(alertaEntity, listType);

		return alertas;
	}

	@Override
	public int save(IEntity entidade) {

		ModelMapper modelMapper = new ModelMapper();
		AlertaEntity alertaEntity = modelMapper.map(entidade, AlertaEntity.class);

		return this.alertaDao.createAlerta(alertaEntity);
	}

	@Override
	public int saveToRelation(IEntity entidade, int relatedId) {

		ModelMapper modelMapper = new ModelMapper();
		AlertaEntity alertaEntity = modelMapper.map(entidade, AlertaEntity.class);

		alertaEntity.metricaId = relatedId;

		return this.alertaDao.createAlerta(alertaEntity);
	}

	@Override
	public IEntity getById(int id) {

		AlertaEntity alertaEntity = this.alertaDao.getAlerta(id);

		ModelMapper modelMapper = new ModelMapper();
		IEntity entidade = modelMapper.map(alertaEntity, IEntity.class);

		return entidade;
	}

	@Override
	public int deleteById(int id) {

		return this.alertaDao.deleteAlerta(id);
	}

	@Override
	public int deleteFromRelation(int relatedId) {
		return this.alertaDao.deleteAlertasFromParent(relatedId);
	}
}
