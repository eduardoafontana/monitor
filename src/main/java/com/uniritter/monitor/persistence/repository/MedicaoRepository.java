package com.uniritter.monitor.persistence.repository;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.repository.IMedicaoRepository;
import com.uniritter.monitor.domain.repository.model.GenericEventData;
import com.uniritter.monitor.domain.repository.model.MedicaoEventData;
import com.uniritter.monitor.persistence.model.MedicaoEntity;
import com.uniritter.monitor.persistence.service.GenericDao;

@Component
public class MedicaoRepository implements IMedicaoRepository {
	
	private GenericDao dao;

	private final JdbcTemplate jdbcTemplate;
		
	@Autowired
	public MedicaoRepository(JdbcTemplate jdbcTemplate) {
		
		this.jdbcTemplate = jdbcTemplate;
		this.dao = new GenericDao(this.jdbcTemplate, "medicao", "metricaid");
	}

	@Override
	public List<? extends GenericEventData> getList() {
		List<MedicaoEntity> medicaoEntity = this.dao.<MedicaoEntity>getList(MedicaoEntity.class);

		ModelMapper modelMapper = new ModelMapper();
		Type listType = new TypeToken<List<MedicaoEventData>>() {
		}.getType();
		List<MedicaoEventData> medicaos = modelMapper.map(medicaoEntity, listType);

		return medicaos;
	}

	@Override
	public List<? extends GenericEventData> getListFromRelation(int relatedId) {
		List<MedicaoEntity> medicaoEntity = this.dao.<MedicaoEntity>getFromParent(MedicaoEntity.class, relatedId);

		ModelMapper modelMapper = new ModelMapper();
		Type listType = new TypeToken<List<MedicaoEventData>>() {
		}.getType();
		List<MedicaoEventData> medicaos = modelMapper.map(medicaoEntity, listType);

		return medicaos;
	}

	@Override
	public int save(GenericEventData entidade) {

		ModelMapper modelMapper = new ModelMapper();
		MedicaoEntity medicaoEntity = modelMapper.map(entidade, MedicaoEntity.class);

		return this.dao.create(medicaoEntity);
	}

	@Override
	public int saveToRelation(GenericEventData entidade, int relatedId) {

		ModelMapper modelMapper = new ModelMapper();
		MedicaoEntity medicaoEntity = modelMapper.map(entidade, MedicaoEntity.class);

		medicaoEntity.setMetricaId(relatedId);

		return this.dao.create(medicaoEntity);
	}

	@Override
	public GenericEventData getById(int id) {

		MedicaoEntity medicaoEntity = this.dao.getById(MedicaoEntity.class, id);

		ModelMapper modelMapper = new ModelMapper();
		GenericEventData entidade = modelMapper.map(medicaoEntity, GenericEventData.class);

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
