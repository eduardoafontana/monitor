package com.uniritter.monitor.persistence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.persistence.model.MetricaEntity;

@Component
public class MetricaDao {
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public MetricaDao(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<MetricaEntity> getMetricas(){  
		return jdbcTemplate.query("select * from metrica order by id", new MetricaRowMapper());
	}
	
	public int createMetrica(MetricaEntity metrica){
		return jdbcTemplate.update("insert into metrica (nome, created) values (?, ?)", metrica.nome, metrica.created);
	}
	
	public boolean isJdbcNotNull(){
		return jdbcTemplate != null;
	}
}
