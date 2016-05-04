package com.uniritter.monitor.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import com.uniritter.monitor.domain.model.*;
import com.uniritter.monitor.domain.service.*;

public class TestMetricaService {

	@Test
	public void testCriarNovaMetrica() {
		MetricaDados metricaDados = new MetricaDados();
		metricaDados.IP = 123456;
		metricaDados.MAC = 111222333;
		metricaDados.Tipo = MetricaTipo.CargaDeRede.toString();
		metricaDados.Grupo = HostGrupo.DbServer.toString();
		
		MetricaService metricaService = new MetricaService();
		Metrica metrica = metricaService.createMetrica(metricaDados);
		
		assertNotNull(metrica);
		assertNotNull(metrica.getHost());
		assertNotNull(metrica.getTipo());
		assertEquals(metrica.getTipo(), MetricaTipo.CargaDeRede);
		assertEquals(metrica.getHost().IP, 123456);
		assertEquals(metrica.getHost().MAC, 111222333);
		assertEquals(metrica.getHost().getGrupo(), HostGrupo.DbServer);
	}

}
