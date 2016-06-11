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
import com.uniritter.monitor.persistence.model.MetricaEntity;
import com.uniritter.monitor.persistence.service.GenericDao;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MonitorApplication.class)
@WebAppConfiguration
public class TestMetricaDao {

	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	@Test
	public void testMetricaDaoGetMetricas() {
		
		GenericDao dao = new GenericDao(jdbcTemplate, "metrica", "");
		
		List<MetricaEntity> metricas = dao.<MetricaEntity>getList(MetricaEntity.class);
		assertNotNull(metricas);
		assertTrue(metricas.size() > 0);
	}
	
	@Test
	public void testMetricaDaoCreateMetrica() {
		
		GenericDao dao = new GenericDao(jdbcTemplate, "metrica", "metricaid");
		
		MetricaEntity metrica = new MetricaEntity();
		metrica.setId(777);
		metrica.setTipo("metrica de teste de repositorio dao");
		metrica.setCreated(new Date());
		
		assertNotEquals(0, dao.create(metrica));
	}
	
	@Test
	public void testDataBase(){
		assertNotNull(jdbcTemplate);
		
		List<MetricaEntity> metricaEntity = this.jdbcTemplate.query("select * from metrica order by id", new BeanPropertyRowMapper<MetricaEntity>(MetricaEntity.class));
		
		assertNotNull(metricaEntity);
	}
}
