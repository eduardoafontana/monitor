package com.uniritter.monitor.persistence.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.repository.IHostRepository;
import com.uniritter.monitor.domain.repository.model.HostEventData;
import com.uniritter.monitor.persistence.service.GenericDao;

@Component
public class HostRepository extends Repository<HostEventData> implements IHostRepository {

	@Autowired
	public HostRepository(JdbcTemplate jdbcTemplate) {
		
		super(new GenericDao(jdbcTemplate, "host", "metricaid"), HostEventData.class);
	}
}
