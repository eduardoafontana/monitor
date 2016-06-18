package com.uniritter.monitor.persistence.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.repository.IMetricaRepository;
import com.uniritter.monitor.domain.repository.model.MetricaEventData;
import com.uniritter.monitor.persistence.service.GenericDao;

@Component
public class MetricaRepository extends Repository<MetricaEventData> implements IMetricaRepository {

	@Autowired
	public MetricaRepository(JdbcTemplate jdbcTemplate) {
		
		super(new GenericDao(jdbcTemplate, "metrica", ""), MetricaEventData.class);
	}
}
