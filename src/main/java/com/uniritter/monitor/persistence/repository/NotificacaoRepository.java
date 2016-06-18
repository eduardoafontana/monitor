package com.uniritter.monitor.persistence.repository;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.repository.INotificacaoRepository;
import com.uniritter.monitor.domain.repository.model.GenericEventData;
import com.uniritter.monitor.domain.repository.model.NotificacaoEventData;
import com.uniritter.monitor.persistence.model.NotificacaoEntity;
import com.uniritter.monitor.persistence.service.GenericDao;

@Component
public class NotificacaoRepository implements INotificacaoRepository {
	
	private GenericDao dao;

	private final JdbcTemplate jdbcTemplate;
		
	@Autowired
	public NotificacaoRepository(JdbcTemplate jdbcTemplate) {
		
		this.jdbcTemplate = jdbcTemplate;
		this.dao = new GenericDao(this.jdbcTemplate, "notificacao", "");
	}

	@Override
	public List<? extends GenericEventData> getList() {
		List<NotificacaoEntity> notificacaoEntity = this.dao.<NotificacaoEntity>getList(NotificacaoEntity.class);

		ModelMapper modelMapper = new ModelMapper();
		Type listType = new TypeToken<List<NotificacaoEventData>>() {
		}.getType();
		List<NotificacaoEventData> notificacaos = modelMapper.map(notificacaoEntity, listType);

		return notificacaos;
	}

	@Override
	public List<? extends GenericEventData> getListFromRelation(int relatedId) {
		return new ArrayList<GenericEventData>();
	}

	@Override
	public int save(GenericEventData entidade) {

		ModelMapper modelMapper = new ModelMapper();
		NotificacaoEntity notificacaoEntity = modelMapper.map(entidade, NotificacaoEntity.class);

		return this.dao.create(notificacaoEntity);
	}

	@Override
	public int saveToRelation(GenericEventData entidade, int relatedId) {

		// lancar execao de objeto nao eh relacionada a alguém

		return 0;
	}

	@Override
	public GenericEventData getById(int id) {

		NotificacaoEntity notificacaoEntity = this.dao.getById(NotificacaoEntity.class, id);

		ModelMapper modelMapper = new ModelMapper();
		GenericEventData entidade = modelMapper.map(notificacaoEntity, GenericEventData.class);

		return entidade;
	}

	@Override
	public int deleteById(int id) {

		return this.dao.delete(id);
	}

	@Override
	public int deleteFromRelation(int relatedId) {
		// lancar execao de objeto nao eh relacionada a alguém

		return 0;
	}
}
