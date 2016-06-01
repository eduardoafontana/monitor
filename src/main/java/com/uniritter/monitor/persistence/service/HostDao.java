package com.uniritter.monitor.persistence.service;

import java.util.ArrayList;
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
	
	public List<HostEntity> getHostsFromParent(int parentId){
		
		Object[] args = {parentId};
		return jdbcTemplate.query("select * from host where metricaid = ?", args, new BeanPropertyRowMapper<HostEntity>(HostEntity.class));
	}	
	
	public int createHost(HostEntity host){
		return jdbcTemplate.update("insert into host (metricaid, ip, mac, grupo, created) values (?, ?, ?, ?, CURRENT_TIMESTAMP())", host.metricaId, host.ip, host.mac, host.grupo);
	}
	
	public HostEntity getHost(int id){
		
		Object[] args = {id};
		return jdbcTemplate.queryForObject("select * from host where id = ?", args, new BeanPropertyRowMapper<HostEntity>(HostEntity.class));
	}

	public int deleteHost(int id){
		return jdbcTemplate.update("delete host where id = ?", id);
	}	
}
