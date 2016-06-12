package com.uniritter.monitor.persistence;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.uniritter.monitor.MonitorApplication;
import com.uniritter.monitor.persistence.model.AlertaEntity;
import com.uniritter.monitor.persistence.service.GenericDao;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MonitorApplication.class)
@WebAppConfiguration
public class TestAlertaDao {

	@Autowired
	public JdbcTemplate jdbcTemplate;
		
	@Test
	public void testAlertaDaoGetList() {
		
		GenericDao dao = new GenericDao(jdbcTemplate, "alerta", "metricaid");
		
		List<AlertaEntity> alertas = dao.<AlertaEntity>getList(AlertaEntity.class);
		assertNotNull(alertas);
		assertTrue(alertas.size() > 0);
	}
	
	@Test
	public void testAlertaDaoGetFromParent() {
		
		GenericDao alertaDao = new GenericDao(jdbcTemplate, "alerta", "metricaid");
		
		List<AlertaEntity> alertas = alertaDao.<AlertaEntity>getFromParent(AlertaEntity.class, 40);
		assertNotNull(alertas);
		assertTrue(alertas.size() > 0);
	}
	
	@Test
	public void testAlertaDaoGetById() {
		
		GenericDao alertaDao = new GenericDao(jdbcTemplate, "alerta", "metricaid");
		
		AlertaEntity alerta = new AlertaEntity();
		alerta.setMetricaId(40);
		alerta.setMensagem("Teste refatoracao persistencia.");
		alerta.setRegra("MenorIgual");
		alerta.setCreated(new Date());
		
		int idAlerta = alertaDao.create(alerta);
		
		AlertaEntity alertaEspecifico = alertaDao.<AlertaEntity>getById(AlertaEntity.class, idAlerta);
		assertNotNull(alertaEspecifico);
		assertEquals(idAlerta, alertaEspecifico.getId());
	}
	
	@Test
	public void testAlertaDaoCreate() {
		
		GenericDao alertaDao = new GenericDao(jdbcTemplate, "alerta", "metricaid");
		
		AlertaEntity alerta = new AlertaEntity();
		alerta.setMetricaId(40);
		alerta.setMensagem("Teste refatoracao persistencia.");
		alerta.setRegra("MenorIgual");
		alerta.setCreated(new Date());
		
		int idAlerta = alertaDao.create(alerta);

		assertNotEquals(0, idAlerta);
		
		AlertaEntity entity = alertaDao.<AlertaEntity>getById(AlertaEntity.class, idAlerta);
		
		assertNotNull(entity);
		assertEquals(idAlerta, entity.getId());
		assertEquals("MenorIgual", entity.getRegra());
		assertEquals("Teste refatoracao persistencia.", entity.getMensagem());
	}
}
