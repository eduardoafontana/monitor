package com.uniritter.monitor.domain;

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
import com.uniritter.monitor.domain.model.*;
import com.uniritter.monitor.domain.service.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MonitorApplication.class)
@WebAppConfiguration
public class TestHostService {

	@Autowired
	public HostService hostService;
	
	@Test
	public void testGetHosts() {
		
		int metricaId = 1;
		
		List<Host> hosts = hostService.getHosts(metricaId);
		
		assertNotNull(hosts);
		assertTrue(hosts.size() > 0);
	}

}
