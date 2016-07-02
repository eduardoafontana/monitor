package com.uniritter.monitor.domain;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.uniritter.monitor.MonitorApplication;
import com.uniritter.monitor.domain.client.model.AlertaClientModel;
import com.uniritter.monitor.domain.model.*;
import com.uniritter.monitor.domain.service.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MonitorApplication.class)
@WebAppConfiguration
public class TestAlertaService {

	@Autowired
	public MetricaService metricaService;

	@Autowired
	public AlertaService alertaService;

	@Test
	public void testGetAlertas() {

		int idMetrica = metricaService.criar(MetricaTipo.EspacoEmDisco);

		assertNotEquals(0, idMetrica);

		for (int i = 0; i < 3; i++) {
			
			AlertaClientModel alertaViewModel = new AlertaClientModel();
			alertaViewModel.mensagem = "O valor é maior " + i + "0.";
			alertaViewModel.regra = "Maior";
			alertaViewModel.valor = i * 10;

			int idAlerta = alertaService.criar(idMetrica, alertaViewModel);

			assertNotEquals(0, idAlerta);
		}

		List<Alerta> alertas = alertaService.getTodos(idMetrica);

		assertNotNull(alertas);
		assertTrue(alertas.size() > 0);
		
		fail("Erro teste");
	}

	@Test
	public void testCreateAlerta() {
		int idMetrica = metricaService.criar(MetricaTipo.EspacoEmDisco);

		assertNotEquals(0, idMetrica);

		AlertaClientModel alertaViewModel = new AlertaClientModel();
		alertaViewModel.mensagem = "O valor é maior 20.";
		alertaViewModel.regra = "Maior";
		alertaViewModel.valor = 20;

		int idAlerta = alertaService.criar(idMetrica, alertaViewModel);

		assertNotEquals(0, idAlerta);
	}
}
