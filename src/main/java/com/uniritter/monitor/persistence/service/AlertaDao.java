package com.uniritter.monitor.persistence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.persistence.model.AlertaEntity;

@Component
public class AlertaDao {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public AlertaDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<AlertaEntity> getAlertas() {

		return jdbcTemplate.query("select * from alerta order by id", new BeanPropertyRowMapper<AlertaEntity>(AlertaEntity.class));
	}

	public List<AlertaEntity> getAlertasFromParent(int parentId) {

		Object[] args = { parentId };
		return jdbcTemplate.query("select * from alerta where metricaid = ?", args, new BeanPropertyRowMapper<AlertaEntity>(AlertaEntity.class));
	}

	public int createAlerta(AlertaEntity alerta) {
		return jdbcTemplate.update( "insert into alerta (metricaid, regra, mensagem, created) values (?, ?, ?, CURRENT_TIMESTAMP())", alerta.getMetricaId(), alerta.getRegra(), alerta.getMensagem());
	}

	public AlertaEntity getAlerta(int id) {

		Object[] args = { id };
		return jdbcTemplate.queryForObject("select * from alerta where id = ?", args, new BeanPropertyRowMapper<AlertaEntity>(AlertaEntity.class));
	}

	public int deleteAlerta(int id) {
		return jdbcTemplate.update("delete alerta where id = ?", id);
	}

	public int deleteAlertasFromParent(int id) {
		return jdbcTemplate.update("delete alerta where metricaid = ?", id);
	}
}
