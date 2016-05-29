package com.uniritter.monitor.persistence;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.uniritter.monitor.MonitorApplication;
import com.uniritter.monitor.domain.model.Metrica;
import com.uniritter.monitor.domain.repository.IEntity;
import com.uniritter.monitor.persistence.repository.MetricaRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MonitorApplication.class)
@WebAppConfiguration
public class TestMetricaRepository {

	@Autowired
	public MetricaRepository metricaRepository;
		
	@Test 
	public void testMetricaRepositoryGetList(){
		List<? extends IEntity> metricas = metricaRepository.getList();
		assertNotNull(metricas);
		assertTrue(metricas.size() > 0);
	}
	
	@Test
	public void testMetricaRepositorySave() {
		Metrica metrica = new Metrica(888, "metrica de teste de repositorio", new Date());		
		
		assertEquals(1, metricaRepository.save(metrica));
	}
}
