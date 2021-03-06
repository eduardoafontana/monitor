package com.uniritter.monitor.persistence.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import com.uniritter.monitor.domain.repository.model.GenericEventData;

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

		try {

			Object[] args = { id };
			return jdbcTemplate.queryForObject("select * from " + this.table + " where id = ?", args,
					new BeanPropertyRowMapper<T>(objClass));

		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}

	public <T> T getLastInserted(Class<T> objClass) {

		try {

			return jdbcTemplate.queryForObject("select top 1 * from " + this.table + " order by id desc",
					new BeanPropertyRowMapper<T>(objClass));

		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}

	public <T> List<T> getFromParent(Class<T> objClass, int parentId) {

		Object[] args = { parentId };
		return jdbcTemplate.query("select * from " + this.table + " where " + this.relatedFieldId + " = ?", args,
				new BeanPropertyRowMapper<T>(objClass));
	}

	public int create(GenericEventData entity) {

		return this.createExecute(entity,
				"insert into " + this.table + " (%1$s, created) values (%2$s, CURRENT_TIMESTAMP())");
	}

	public int create(GenericEventData entity, int relatedId) {

		return this.createExecute(entity, "insert into " + this.table + " (%1$s, " + this.relatedFieldId
				+ ", created) values (%2$s, " + relatedId + ", CURRENT_TIMESTAMP())");
	}

	private int createExecute(GenericEventData entity, String sql) {

		List<String> names = new ArrayList<String>();
		List<String> separators = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();

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
			values.add(value);
		}

		String formattedSql = String.format(sql, StringUtils.arrayToCommaDelimitedString(names.toArray()),
				StringUtils.arrayToCommaDelimitedString(separators.toArray()));

		Object[] args = values.toArray();

		if (jdbcTemplate.update(formattedSql, args) > 0)
			return jdbcTemplate.queryForObject("select last_insert_id()", int.class);
		else
			throw new NoRowsAffected("Nenhuma linha afedata para insert into");
	}

	public boolean registerExist(int id) {

		Object[] args = { id };
		int registerCount = jdbcTemplate.queryForObject("select count(*) from " + this.table + " where id = ?", args,
				Integer.class);

		return registerCount > 0;
	}
	
	public int update(GenericEventData entity) {

		return this.updateExecute(entity, "update " + this.table + " set %1$s where id = " + entity.getId());
	}

	public int update(GenericEventData entity, int relatedId) {

		return this.updateExecute(entity, "update " + this.table + " set %1$s, "+ this.relatedFieldId + " = "+relatedId+" where id = " + entity.getId());
	}

	public int updateExecute(GenericEventData entity, String sql) {
		
		List<String> names = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();

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

			names.add(field.getName() + " = ? ");
			values.add(value);
		}

		String formattedSql = String.format(sql, StringUtils.arrayToCommaDelimitedString(names.toArray()));

		Object[] args = values.toArray();

		if (jdbcTemplate.update(formattedSql, args) > 0)
			return entity.getId();
		else
			throw new NoRowsAffected("Nenhuma linha afedata para insert into");
	}
}
