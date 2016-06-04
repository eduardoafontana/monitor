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
		int id = metricaService.createMetrica(MetricaTipo.CPU);
		
		assertNotEquals(id, 0);
	}
	
	@Test
	public void testGetMetricas() {
		
		List<Metrica> metricas = metricaService.getMetricas();
		
		assertNotNull(metricas);
		assertTrue(metricas.size() > 0);
	}
	
	@Test
	public void testGetMetricaComHosts() {
		
		Metrica metrica = metricaService.getMetrica(1);
		
		assertNotNull(metrica);
		
		assertTrue(metrica.getHosts().size() > 0);
	}
	
	@Test
	public void testDeleteMetricaEHosts(){
		int idMetrica = metricaService.createMetrica(MetricaTipo.EspacoEmDisco);
		
		assertNotEquals(idMetrica, 0);
		
		HostData hostData = new HostData();
		hostData.ip = 123465;
		hostData.mac = 54321;
		hostData.grupo = "Firewall";
		
		int idHost = hostService.addHost(idMetrica, hostData);
		
		assertNotEquals(idHost, 0);
		
		int rowsAffeted = metricaService.deleteMetrica(idMetrica);
		
		assertEquals(rowsAffeted, 1);
		
		List<Host> hosts = hostService.getHosts(idMetrica);
		
		assertEquals(hosts.size(), 0);
	}
}
