package com.uniritter.monitor.persistence.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import com.uniritter.monitor.persistence.model.GenericEntity;

public class GenericDao {
	
	private JdbcTemplate jdbcTemplate;
	private String table;
	private String relatedFieldId;

	public GenericDao(JdbcTemplate jdbcTemplate, String table, String relatedFieldId) {
		this.jdbcTemplate = jdbcTemplate;
		this.table = table;
		this.relatedFieldId = relatedFieldId;
	}

	public int deleteFromParent(int id) {
		return jdbcTemplate.update("delete " + this.table + " where " + this.relatedFieldId + " = ?", id);
	}

	public int delete(int id) {
		return jdbcTemplate.update("delete " + this.table + " where id = ?", id);
	}

	public <T> List<T> getList(Class<T> objClass) {
		return jdbcTemplate.query("select * from " + this.table + " order by id",
				new BeanPropertyRowMapper<T>(objClass));
	}

	public <T> T getById(Class<T> objClass, int id) {

		Object[] args = { id };
		return jdbcTemplate.queryForObject("select * from " + this.table + " where id = ?", args,
				new BeanPropertyRowMapper<T>(objClass));
	}

	public <T> List<T> getFromParent(int parentId) {

		Object[] args = { parentId };
		return jdbcTemplate.query("select * from " + this.table + " where " + this.relatedFieldId + " = ?", args,
				new BeanPropertyRowMapper<T>());
	}

	public int createAlerta(GenericEntity entity) {

		List<String> names = new ArrayList<String>();
		List<String> separators = new ArrayList<String>();
		List<String> values = new ArrayList<String>();

		Class<?> classe = entity.getClass();

		for (Field field : classe.getDeclaredFields()) {

			Object value = null;

			field.setAccessible(true);

			try {
				value = field.get(entity);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			names.add(field.getName());
			separators.add("?");
			values.add(String.valueOf(value));
		}

		String sql = "insert into " + this.table + " (" + StringUtils.arrayToCommaDelimitedString(names.toArray())
				+ ", created) values (" + StringUtils.arrayToCommaDelimitedString(separators.toArray())
				+ ", CURRENT_TIMESTAMP())";

		Object[] args = values.toArray();

		if (jdbcTemplate.update(sql, args) > 0)
			return jdbcTemplate.queryForObject("select last_insert_id()", int.class);
		else
			// throw new NoRowsAffected("Nenhuma linha afedata para insert into
			// metrica (nome, created) values (?, ?)");
			return 0;
	}
}
