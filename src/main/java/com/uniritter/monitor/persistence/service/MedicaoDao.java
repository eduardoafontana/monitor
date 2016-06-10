package com.uniritter.monitor.persistence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.persistence.model.MedicaoEntity;

@Component
public class MedicaoDao {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public MedicaoDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<MedicaoEntity> getMedicaos() {

		return jdbcTemplate.query("select * from medicao order by id", new BeanPropertyRowMapper<MedicaoEntity>(MedicaoEntity.class));
	}

	public List<MedicaoEntity> getMedicaosFromParent(int parentId) {

		Object[] args = { parentId };
		return jdbcTemplate.query("select * from medicao where metricaid = ?", args, new BeanPropertyRowMapper<MedicaoEntity>(MedicaoEntity.class));
	}

	public int createMedicao(MedicaoEntity medicao) {
		return jdbcTemplate.update( "insert into medicao (metricaid, mac, quando, valor, created) values (?, ?, ?, ?, CURRENT_TIMESTAMP())", medicao.getMetricaId(), medicao.getMac(), medicao.getQuando(), medicao.getValor());
	}

	public MedicaoEntity getMedicao(int id) {

		Object[] args = { id };
		return jdbcTemplate.queryForObject("select * from medicao where id = ?", args, new BeanPropertyRowMapper<MedicaoEntity>(MedicaoEntity.class));
	}

	public int deleteMedicao(int id) {
		return jdbcTemplate.update("delete medicao where id = ?", id);
	}

	public int deleteMedicaosFromParent(int id) {
		return jdbcTemplate.update("delete medicao where metricaid = ?", id);
	}
}
