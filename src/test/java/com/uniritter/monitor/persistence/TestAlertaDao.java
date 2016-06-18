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
import com.uniritter.monitor.domain.repository.model.AlertaEventData;
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
		
		List<AlertaEventData> alertas = dao.<AlertaEventData>getList(AlertaEventData.class);
		assertNotNull(alertas);
		assertTrue(alertas.size() > 0);
	}
	
	@Test
	public void testAlertaDaoGetFromParent() {
		
		GenericDao alertaDao = new GenericDao(jdbcTemplate, "alerta", "metricaid");
		
		List<AlertaEventData> alertas = alertaDao.<AlertaEventData>getFromParent(AlertaEventData.class, 40);
		assertNotNull(alertas);
		assertTrue(alertas.size() > 0);
	}
	
	@Test
	public void testAlertaDaoGetById() {
		
		GenericDao alertaDao = new GenericDao(jdbcTemplate, "alerta", "metricaid");
		
		AlertaEventData alerta = new AlertaEventData();
		alerta.setMensagem("Teste refatoracao persistencia.");
		alerta.setRegra("MenorIgual");
		alerta.setCreated(new Date());
		
		int idAlerta = alertaDao.create(alerta, 40);
		
		AlertaEventData alertaEspecifico = alertaDao.<AlertaEventData>getById(AlertaEventData.class, idAlerta);
		assertNotNull(alertaEspecifico);
		assertEquals(idAlerta, alertaEspecifico.getId());
	}
	
	@Test
	public void testAlertaDaoCreate() {
		
		GenericDao alertaDao = new GenericDao(jdbcTemplate, "alerta", "metricaid");
		
		AlertaEventData alerta = new AlertaEventData();
		alerta.setMensagem("Teste refatoracao persistencia.");
		alerta.setRegra("MenorIgual");
		alerta.setCreated(new Date());
		
		int idAlerta = alertaDao.create(alerta, 40);

		assertNotEquals(0, idAlerta);
		
		AlertaEventData entity = alertaDao.<AlertaEventData>getById(AlertaEventData.class, idAlerta);
		
		assertNotNull(entity);
		assertEquals(idAlerta, entity.getId());
		assertEquals("MenorIgual", entity.getRegra());
		assertEquals("Teste refatoracao persistencia.", entity.getMensagem());
	}
}
