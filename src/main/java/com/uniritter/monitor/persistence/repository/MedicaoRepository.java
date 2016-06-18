package com.uniritter.monitor.persistence.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.repository.IMedicaoRepository;
import com.uniritter.monitor.domain.repository.model.MedicaoEventData;
import com.uniritter.monitor.persistence.service.GenericDao;

@Component
public class MedicaoRepository extends Repository<MedicaoEventData> implements IMedicaoRepository {

	@Autowired
	public MedicaoRepository(JdbcTemplate jdbcTemplate) {
		
		super(new GenericDao(jdbcTemplate, "medicao", "metricaid"), MedicaoEventData.class);
	}
}
