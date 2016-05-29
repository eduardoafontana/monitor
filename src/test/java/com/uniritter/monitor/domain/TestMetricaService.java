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
public class TestMetricaService {

	@Autowired
	public MetricaService metricaService;
	
	@Test
	public void testCriarNovaMetrica() {
//		MetricaDados metricaDados = new MetricaDados();
//		metricaDados.IP = 123456;
//		metricaDados.MAC = 111222333;
//		metricaDados.Tipo = MetricaTipo.CargaDeRede.toString();
//		metricaDados.Grupo = HostGrupo.DbServer.toString();
//		
//		MetricaService metricaService = new MetricaService();
//		Metrica metrica = metricaService.createMetrica(metricaDados);
//		
//		assertNotNull(metrica);
//		assertNotNull(metrica.getHost());
//		assertNotNull(metrica.getTipo());
//		assertEquals(metrica.getTipo(), MetricaTipo.CargaDeRede);
//		assertEquals(metrica.getHost().IP, 123456);
//		assertEquals(metrica.getHost().MAC, 111222333);
//		assertEquals(metrica.getHost().getGrupo(), HostGrupo.DbServer);
	}
	
	@Test
	public void testMetricaService() {
		
		List<Metrica> metricas = metricaService.getMetricas();
		
		MetricaDados metricaDados = new MetricaDados();
		metricaDados.id = 999;
		metricaDados.nome = "metrica de teste de repositorio";
		metricaDados.created = new Date();
		
		metricaService.createMetrica(metricaDados);
		
		assertTrue(metricaService.getMetricas().size() > metricas.size());
		assertTrue((metricaService.getMetricas().size() - 1) == metricas.size());
	}

}
