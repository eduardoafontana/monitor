package com.uniritter.monitor.domain;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.uniritter.monitor.MonitorApplication;
import com.uniritter.monitor.domain.client.model.HostViewModel;
import com.uniritter.monitor.domain.model.*;
import com.uniritter.monitor.domain.service.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MonitorApplication.class)
@WebAppConfiguration
public class TestMetricaService {

	@Autowired
	public MetricaService metricaService;
	
	@Autowired
	public HostService hostService;
	
	@Test
	public void testCriarNovaMetrica() {	
		int id = metricaService.create(MetricaTipo.CPU);
		
		assertNotEquals(id, 0);
	}
	
	@Test
	public void testGetMetrica() {	
		Metrica metrica = metricaService.retrieve(2);
		
		assertNotNull(metrica);
		assertEquals(metrica.getId(), 2);
	}
	
	@Test
	public void testGetMetricas() {
		
		List<Metrica> metricas = metricaService.retrieveAll();
		
		assertNotNull(metricas);
		assertTrue(metricas.size() > 0);
	}
	
	@Test
	public void testGetMetricaComHosts() {
		
		Metrica metrica = metricaService.retrieve(2);
		
		assertNotNull(metrica);
		
		assertTrue(metrica.getHosts().size() > 0);
	}
	
	@Test
	public void testDeleteMetricaEHosts(){
		int idMetrica = metricaService.create(MetricaTipo.EspacoEmDisco);
		
		assertNotEquals(idMetrica, 0);
		
		HostViewModel hostData = new HostViewModel();
		hostData.ip = 123465;
		hostData.mac = 54321;
		hostData.grupo = "Firewall";
		
		int idHost = hostService.create(idMetrica, hostData);
		
		assertNotEquals(idHost, 0);
		
		int rowsAffeted = metricaService.remove(idMetrica);
		
		assertTrue(rowsAffeted >= 2);
		
		List<Host> hosts = hostService.retrieveAll(idMetrica);
		
		assertEquals(hosts.size(), 0);
	}
}
