package com.uniritter.monitor.persistence.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.repository.INotificacaoRepository;
import com.uniritter.monitor.domain.repository.model.NotificacaoEventData;
import com.uniritter.monitor.persistence.service.GenericDao;

@Component
public class NotificacaoRepository extends Repository<NotificacaoEventData> implements INotificacaoRepository {

	@Autowired
	public NotificacaoRepository(JdbcTemplate jdbcTemplate) {
		
		super(new GenericDao(jdbcTemplate, "notificacao", ""), NotificacaoEventData.class);
	}
	
	@Override
	public NotificacaoEventData getLast() {

		return super.dao.<NotificacaoEventData>getLastInserted(NotificacaoEventData.class);
	}
}
