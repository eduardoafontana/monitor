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
import com.uniritter.monitor.domain.repository.model.MedicaoEventData;
import com.uniritter.monitor.persistence.service.GenericDao;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MonitorApplication.class)
@WebAppConfiguration
public class TestMedicaoDao {

	@Autowired
	public JdbcTemplate jdbcTemplate;
		
	@Test
	public void testMedicaoDaoGetList() {
		
		GenericDao dao = new GenericDao(jdbcTemplate, "medicao", "metricaid");
		
		List<MedicaoEventData> medicoes = dao.<MedicaoEventData>getList(MedicaoEventData.class);
		assertNotNull(medicoes);
		assertTrue(medicoes.size() > 0);
	}
	
	@Test
	public void testMedicaoDaoGetFromParent() {
		
		GenericDao medicaoDao = new GenericDao(jdbcTemplate, "medicao", "metricaid");
		
		List<MedicaoEventData> medicoes = medicaoDao.<MedicaoEventData>getFromParent(MedicaoEventData.class, 40);
		assertNotNull(medicoes);
		assertTrue(medicoes.size() > 0);
	}
	
	@Test
	public void testMedicaoDaoGetById() {
		
		GenericDao medicaoDao = new GenericDao(jdbcTemplate, "medicao", "metricaid");
		
		MedicaoEventData medicao = new MedicaoEventData();
		medicao.setMac(123456);
		medicao.setValor(50);;
		medicao.setQuando(new Date());
		medicao.setCreated(new Date());
		
		int idMedicao = medicaoDao.create(medicao, 40);
		
		MedicaoEventData medicaoEspecifico = medicaoDao.<MedicaoEventData>getById(MedicaoEventData.class, idMedicao);
		assertNotNull(medicaoEspecifico);
		assertEquals(idMedicao, medicaoEspecifico.getId());
	}
	
	@Test
	public void testMedicaoDaoCreate() {
		
		GenericDao medicaoDao = new GenericDao(jdbcTemplate, "medicao", "metricaid");
		
		MedicaoEventData medicao = new MedicaoEventData();
		medicao.setMac(123456);
		medicao.setValor(50);;
		medicao.setQuando(new Date());
		medicao.setCreated(new Date());
		
		int idMedicao = medicaoDao.create(medicao, 40);

		assertNotEquals(0, idMedicao);
		
		MedicaoEventData entity = medicaoDao.<MedicaoEventData>getById(MedicaoEventData.class, idMedicao);
		
		assertNotNull(entity);
		assertEquals(idMedicao, entity.getId());
		assertEquals(50, entity.getValor());
		assertEquals(123456, entity.getMac());
	}
}
