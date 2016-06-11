package com.uniritter.monitor.persistence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.persistence.model.AlertaEntity;
import com.uniritter.monitor.persistence.model.GenericEntity;

public abstract class GenericDao {

	private JdbcTemplate jdbcTemplate;
	private String table;
	private String relatedFieldId;

	public GenericDao(JdbcTemplate jdbcTemplate, String table, String relatedFieldId) {
		this.jdbcTemplate = jdbcTemplate;
		this.table = table;
		this.relatedFieldId = relatedFieldId;
	}

	public int deleteFromParent(int id) {
		return jdbcTemplate.update("delete " + this.table + " where " + relatedFieldId + " = ?", id);
	}
	
	public int delete(int id) {
		return jdbcTemplate.update("delete " + this.table + " where id = ?", id);
	}
	
	public <T> List<T> getList() {
		return jdbcTemplate.query("select * from " + this.table + " order by id", new BeanPropertyRowMapper<T>());
	}
	
	public <T> T getById(int id) {

		Object[] args = { id };
		return jdbcTemplate.queryForObject("select * from " + this.table + " where id = ?", args, new BeanPropertyRowMapper<T>());
	}	
}
