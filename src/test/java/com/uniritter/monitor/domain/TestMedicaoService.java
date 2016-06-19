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
import com.uniritter.monitor.domain.client.model.AlertaClientModel;
import com.uniritter.monitor.domain.client.model.MedicaoClientModel;
import com.uniritter.monitor.domain.model.*;
import com.uniritter.monitor.domain.service.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MonitorApplication.class)
@WebAppConfiguration
public class TestMedicaoService {

	@Autowired
	public MetricaService metricaService;
	
	@Autowired
	public MedicaoService medicaoService;
	
	@Autowired
	public AlertaService alertaService;

	@Autowired
	public NotificacaoService notificacaoService;	
	
	@Test
	public void testGetMedicoes() {
		
		List<Medicao> medicoes = medicaoService.getTodos(144);
		
		assertNotNull(medicoes);
		assertTrue(medicoes.size() > 0);
	}
	
	@Test
	public void testCreateMedicao(){
		int idMetrica = metricaService.criar(MetricaTipo.EspacoEmDisco);
		
		assertNotEquals(0, idMetrica);
		
		MedicaoClientModel medicaoViewModel = new MedicaoClientModel();
		medicaoViewModel.mac = 123456;
		medicaoViewModel.quando = new Date();
		medicaoViewModel.valor = 10;
		
		int idMedicao = medicaoService.criar(idMetrica, medicaoViewModel, metricaService);
		
		assertNotEquals(0, idMedicao);
	}
	
	@Test
	public void testDisparaNotificaoAlertaValidoParaMedicaoRealizada(){
		
		int idMetrica = metricaService.criar(MetricaTipo.Memoria);
		
		assertNotEquals(idMetrica, 0);
		
		AlertaClientModel alertaViewModel = new AlertaClientModel();
		alertaViewModel.mensagem = "O valor é igual a 80.";
		alertaViewModel.regra = "Igual";
		alertaViewModel.valor = 80;

		int idAlerta = alertaService.criar(idMetrica, alertaViewModel);

		assertNotEquals(0, idAlerta);
		
		MedicaoClientModel medicaoViewModel = new MedicaoClientModel();
		medicaoViewModel.mac = 147;
		medicaoViewModel.quando = new Date();
		medicaoViewModel.valor = 80;
		
		int idMedicao = medicaoService.criar(idMetrica, medicaoViewModel, metricaService);
		
		assertNotEquals(0, idMedicao);
		assertEquals(alertaViewModel.mensagem, notificacaoService.getUltimaMensagem());
	}
}
