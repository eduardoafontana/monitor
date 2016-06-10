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
import com.uniritter.monitor.domain.client.model.AlertaViewModel;
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
		
		List<Alerta> alertas = alertaService.retrieveAll(40);
		
		assertNotNull(alertas);
		assertTrue(alertas.size() > 0);
	}
	
	@Test
	public void testCreateAlerta(){
		int idMetrica = metricaService.create(MetricaTipo.EspacoEmDisco);
		
		assertNotEquals(0, idMetrica);
		
		AlertaViewModel alertaViewModel = new AlertaViewModel();
		alertaViewModel.mensagem = "O valor é maior ou igual a 50.";
		alertaViewModel.regra = "MaiorIgual";
		
		int idAlerta = alertaService.create(idMetrica, alertaViewModel);
		
		assertNotEquals(0, idAlerta);
	}
}
