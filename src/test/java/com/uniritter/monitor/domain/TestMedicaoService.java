package com.uniritter.monitor.domain;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
import com.uniritter.monitor.domain.client.model.MedicaoViewModel;
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
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	@Test
	public void testGetMedicoes() {
		
		List<Medicao> medicoes = medicaoService.retrieveAll(144);
		
		assertNotNull(medicoes);
		assertTrue(medicoes.size() > 0);
	}
	
	@Test
	public void testCreateMedicao(){
		int idMetrica = metricaService.create(MetricaTipo.EspacoEmDisco);
		
		assertNotEquals(0, idMetrica);
		
		MedicaoViewModel medicaoViewModel = new MedicaoViewModel();
		medicaoViewModel.mac = 123456;
		medicaoViewModel.quando = new Date();
		medicaoViewModel.valor = 10;
		
		int idMedicao = medicaoService.create(idMetrica, medicaoViewModel, metricaService);
		
		assertNotEquals(0, idMedicao);
	}
	
	@Test
	public void testDisparaNotificaoAlertaValidoParaMedicaoRealizada(){
		
		//System.setOut(new PrintStream(outContent));
		
		int idMetrica = metricaService.create(MetricaTipo.Memoria);
		
		assertNotEquals(idMetrica, 0);
		
		AlertaViewModel alertaViewModel = new AlertaViewModel();
		alertaViewModel.mensagem = "O valor é igual a 80.";
		alertaViewModel.regra = "Igual";
		alertaViewModel.valor = 80;

		int idAlerta = alertaService.create(idMetrica, alertaViewModel);

		assertNotEquals(0, idAlerta);
		
		MedicaoViewModel medicaoViewModel = new MedicaoViewModel();
		medicaoViewModel.mac = 147;
		medicaoViewModel.quando = new Date();
		medicaoViewModel.valor = 80;
		
		int idMedicao = medicaoService.create(idMetrica, medicaoViewModel, metricaService);
		
		assertNotEquals(0, idMedicao);
		assertEquals(alertaViewModel.mensagem + System.lineSeparator(), outContent.toString());
		
		//TODO: Gravar a notificacao na tabela de notificacao porque a saida nao funciona
		
		//System.setOut(null);
	}
}
