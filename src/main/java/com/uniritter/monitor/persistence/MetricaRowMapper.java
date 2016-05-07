package com.uniritter.monitor.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

import com.uniritter.monitor.domain.model.Metrica;

public class MetricaRowMapper implements RowMapper<Metrica> {

	@Override
	public Metrica mapRow(ResultSet rs, int rowNum) throws SQLException, DataAccessException {
		return new Metrica(rs.getLong("id"), rs.getString("nome"), rs.getDate("created"));
	}

}
