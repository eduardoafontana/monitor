package com.uniritter.monitor.persistence;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.uniritter.monitor.MonitorApplication;
import com.uniritter.monitor.domain.repository.model.MetricaEventData;
import com.uniritter.monitor.persistence.service.GenericDao;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MonitorApplication.class)
@WebAppConfiguration
public class TestMetricaDao {

	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	@Test
	public void testMetricaDaoGetList() {
		
		GenericDao dao = new GenericDao(jdbcTemplate, "metrica", "");
		
		List<MetricaEventData> metricas = dao.<MetricaEventData>getList(MetricaEventData.class);
		assertNotNull(metricas);
		assertTrue(metricas.size() > 0);
	}
	
	@Test
	public void testMetricaDaoCreate() {
		
		GenericDao dao = new GenericDao(jdbcTemplate, "metrica", "metricaid");
		
		MetricaEventData metrica = new MetricaEventData();
		metrica.setId(777);
		metrica.setTipo("metrica de teste de repositorio dao");
		metrica.setCreated(new Date());
		
		assertNotEquals(0, dao.create(metrica));
	}
	
	@Test
	public void testMetricaDaoRegisterExist() {
		
		GenericDao dao = new GenericDao(jdbcTemplate, "metrica", "metricaid");
		
		assertTrue(dao.registerExist(2));
	}
	
	@Test
	public void testMetricaDaoRegisterNotExist() {
		
		GenericDao dao = new GenericDao(jdbcTemplate, "metrica", "metricaid");
		
		assertFalse(dao.registerExist(9999999));
	}
	
	@Test
	public void testDataBase(){
		assertNotNull(jdbcTemplate);
		
		List<MetricaEventData> metricaEntity = this.jdbcTemplate.query("select * from metrica order by id", new BeanPropertyRowMapper<MetricaEventData>(MetricaEventData.class));
		
		assertNotNull(metricaEntity);
	}
}
