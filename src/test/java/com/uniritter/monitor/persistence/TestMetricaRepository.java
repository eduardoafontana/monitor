package com.uniritter.monitor.persistence;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.uniritter.monitor.MonitorApplication;
import com.uniritter.monitor.domain.repository.model.GenericEventData;
import com.uniritter.monitor.persistence.repository.MetricaRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MonitorApplication.class)
@WebAppConfiguration
public class TestMetricaRepository {

	@Autowired
	public MetricaRepository metricaRepository;
		
	@Test 
	public void testMetricaRepositoryGetList(){
		List<? extends GenericEventData> metricas = metricaRepository.getList();
		assertNotNull(metricas);
		assertTrue(metricas.size() > 0);
	}
}
