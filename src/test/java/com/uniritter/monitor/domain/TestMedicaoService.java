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
		
		int idMedicao = medicaoService.create(idMetrica, medicaoViewModel);
		
		assertNotEquals(0, idMedicao);
	}
}
