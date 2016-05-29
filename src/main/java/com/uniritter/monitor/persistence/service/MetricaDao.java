package com.uniritter.monitor.persistence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.persistence.model.HostEntity;
import com.uniritter.monitor.persistence.model.MetricaEntity;

@Component
public class MetricaDao {
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public MetricaDao(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<MetricaEntity> getMetricas(){
		
		return jdbcTemplate.query("select * from metrica order by id", new BeanPropertyRowMapper<MetricaEntity>(MetricaEntity.class));
	}	
	
	public int createMetrica(MetricaEntity metrica) {
		if(jdbcTemplate.update("insert into metrica (tipo, created) values (?, CURRENT_TIMESTAMP())", metrica.tipo) > 0)
			return jdbcTemplate.queryForObject("select last_insert_id()", int.class);
		else 
			//throw new NoRowsAffected("Nenhuma linha afedata para insert into metrica (nome, created) values (?, ?)");
			return 0;
	}
	
	public MetricaEntity getMetrica(int id){
		
		try{
			Object[] args = {id};
			return jdbcTemplate.queryForObject("select * from metrica where id = ?", args, new BeanPropertyRowMapper<MetricaEntity>(MetricaEntity.class));
		}
		catch(EmptyResultDataAccessException ex){
			return null;
		}
	}	
}
