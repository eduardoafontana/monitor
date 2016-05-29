package com.uniritter.monitor.persistence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.persistence.model.HostEntity;

@Component
public class HostDao {
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public HostDao(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<HostEntity> getHosts(){
		
		return jdbcTemplate.query("select * from host order by id", new BeanPropertyRowMapper<HostEntity>(HostEntity.class));
	}
	
	public int createHost(HostEntity host){
		return jdbcTemplate.update("insert into host (ip, mac) values (?, ?)", host.ip, host.mac);
	}
}
