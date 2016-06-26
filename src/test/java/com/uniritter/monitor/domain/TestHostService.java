package com.uniritter.monitor.domain;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.uniritter.monitor.MonitorApplication;
import com.uniritter.monitor.domain.client.model.HostClientModel;
import com.uniritter.monitor.domain.model.*;
import com.uniritter.monitor.domain.service.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MonitorApplication.class)
@WebAppConfiguration
public class TestHostService {

	@Autowired
	public MetricaService metricaService;
	
	@Autowired
	public HostService hostService;
	
	@Test(expected=DataIntegrityViolationException.class)
	public void testSalvarHostVazio(){
		Host host = new Host(hostService);
		host.save(2);
	}
	
	@Test
	public void testGetHosts() {
		
		List<Host> hosts = hostService.getTodos(2);
		
		assertNotNull(hosts);
		assertTrue(hosts.size() > 0);
	}
	
	@Test
	public void testCreateHost(){
		int idMetrica = metricaService.criar(MetricaTipo.EspacoEmDisco);
		
		assertNotEquals(0, idMetrica);
		
		HostClientModel hostData = new HostClientModel();
		hostData.ip = 123465;
		hostData.mac = 54321;
		hostData.grupo = "Firewall";
		
		int idHost = hostService.criar(idMetrica, hostData);
		
		assertNotEquals(0, idHost);
	}
}
