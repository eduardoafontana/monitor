package com.uniritter.monitor.persistence.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

import com.uniritter.monitor.persistence.model.HostEntity;

public class HostRowMapper implements RowMapper<HostEntity> {

	@Override
	public HostEntity mapRow(ResultSet rs, int rowNum) throws SQLException, DataAccessException {
		return new HostEntity(rs.getLong("id"), rs.getInt("ip"), rs.getInt("mac"));
	}

}
