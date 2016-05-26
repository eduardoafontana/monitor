package com.uniritter.monitor.persistence.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

import com.uniritter.monitor.persistence.model.MetricaEntity;

public class MetricaRowMapper implements RowMapper<MetricaEntity> {

	@Override
	public MetricaEntity mapRow(ResultSet rs, int rowNum) throws SQLException, DataAccessException {
		return new MetricaEntity(rs.getLong("id"), rs.getString("nome"), rs.getDate("created"));
	}

}
