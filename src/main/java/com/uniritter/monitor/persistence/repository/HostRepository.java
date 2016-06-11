package com.uniritter.monitor.persistence.repository;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.model.Host;
import com.uniritter.monitor.domain.repository.IHostRepository;
import com.uniritter.monitor.domain.repository.model.HostEventData;
import com.uniritter.monitor.domain.repository.model.GenericEventData;
import com.uniritter.monitor.persistence.model.HostEntity;
import com.uniritter.monitor.persistence.service.GenericDao;

@Component
public class HostRepository implements IHostRepository {

	private GenericDao dao;

	private final JdbcTemplate jdbcTemplate;
		
	@Autowired
	public HostRepository(JdbcTemplate jdbcTemplate) {
		
		this.jdbcTemplate = jdbcTemplate;
		this.dao = new GenericDao(this.jdbcTemplate, "host", "metricaid");
	}
	
	@Override
	public List<? extends GenericEventData> getList() {
		List<HostEntity> hostEntity = this.dao.<HostEntity>getList(HostEntity.class);

		ModelMapper modelMapper = new ModelMapper();
		Type listType = new TypeToken<List<Host>>() {
		}.getType();
		List<HostEventData> hosts = modelMapper.map(hostEntity, listType);

		return hosts;
	}

	@Override
	public List<? extends GenericEventData> getListFromRelation(int relatedId) {
		List<HostEntity> hostEntity = this.dao.<HostEntity>getFromParent(HostEntity.class, relatedId);

		ModelMapper modelMapper = new ModelMapper();
		Type listType = new TypeToken<List<HostEventData>>() {
		}.getType();
		List<HostEventData> hosts = modelMapper.map(hostEntity, listType);

		return hosts;
	}

	@Override
	public int save(GenericEventData entidade) {

		ModelMapper modelMapper = new ModelMapper();
		HostEntity hostEntity = modelMapper.map(entidade, HostEntity.class);

		return this.dao.create(hostEntity);
	}

	@Override
	public int saveToRelation(GenericEventData entidade, int relatedId) {

		ModelMapper modelMapper = new ModelMapper();
		HostEntity hostEntity = modelMapper.map(entidade, HostEntity.class);

		hostEntity.setMetricaId(relatedId);

		return this.dao.create(hostEntity);
	}

	@Override
	public GenericEventData getById(int id) {

		HostEntity hostEntity = this.dao.<HostEntity>getById(HostEntity.class, id);

		ModelMapper modelMapper = new ModelMapper();
		GenericEventData entidade = modelMapper.map(hostEntity, GenericEventData.class);

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
