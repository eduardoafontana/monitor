package com.uniritter.monitor.persistence.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.repository.IAlertaRepository;
import com.uniritter.monitor.domain.repository.model.AlertaEventData;
import com.uniritter.monitor.persistence.service.GenericDao;

@Component
public class AlertaRepository extends Repository<AlertaEventData> implements IAlertaRepository {

	@Autowired
	public AlertaRepository(JdbcTemplate jdbcTemplate) {

		super(new GenericDao(jdbcTemplate, "alerta", "metricaid"), AlertaEventData.class);
	}
}
