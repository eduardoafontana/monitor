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
import com.uniritter.monitor.common.NoResultFound;
import com.uniritter.monitor.domain.client.model.HostClientModel;
import com.uniritter.monitor.domain.client.model.MedicaoClientModel;
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

	@Autowired
	public MedicaoService medicaoService;

	@Test
	public void testCriarNovaMetrica() {
		int id = metricaService.criar(MetricaTipo.CPU);

		assertNotEquals(id, 0);
	}

	@Test
	public void testGetMetrica() {

		try {
			Metrica metrica = metricaService.getUnico(2);

			assertNotNull(metrica);
			assertEquals(metrica.getId(), 2);
		} catch (NoResultFound e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetMetricas() {

		List<Metrica> metricas = metricaService.getTodos();

		assertNotNull(metricas);
		assertTrue(metricas.size() > 0);
	}

	@Test
	public void testGetMetricaComHosts() {

		try {
			Metrica metrica = metricaService.getUnico(2);

			assertNotNull(metrica);
			assertTrue(metrica.getHosts().size() > 0);
		} catch (NoResultFound e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testAddHostNaMetrica() {

		Metrica metrica = null;

		try {
			metrica = metricaService.getUnico(2);
		} catch (NoResultFound e) {
			fail(e.getMessage());
		}
		
		assertNotNull(metrica);

		int sizeHostAnterior = metrica.getHosts().size();

		Host novoHost = new Host(hostService);
		novoHost.setIp(1965);
		novoHost.setGrupo(HostGrupo.WebServer);
		novoHost.setMac(3241);
		
		List<Host> listHost = metrica.getHosts();
		listHost.add(novoHost);

		metrica.save();
		
		metrica = null;
		
		try {
			metrica = metricaService.getUnico(2);
		} catch (NoResultFound e) {
			fail(e.getMessage());
		}
		
		int sizeHostAtual = metrica.getHosts().size();
		
		assertEquals(sizeHostAnterior + 1, sizeHostAtual);
	}

	@Test
	public void testDeleteMetricaEHosts() {
		int idMetrica = metricaService.criar(MetricaTipo.EspacoEmDisco);

		assertNotEquals(idMetrica, 0);

		HostClientModel hostData = new HostClientModel();
		hostData.ip = 123465;
		hostData.mac = 54321;
		hostData.grupo = "Firewall";

		int idHost = hostService.criar(idMetrica, hostData);

		assertNotEquals(idHost, 0);

		try {

			int rowsAffeted = metricaService.apagar(idMetrica);

			assertEquals(1, rowsAffeted);
		} catch (NoResultFound e) {

			fail(e.getMessage());
		}

		List<Host> hosts = hostService.getTodos(idMetrica);

		assertEquals(0, hosts.size());
	}

	@Test
	public void testMetricaGetHistoricoMedicoes() {

		try {

			List<Medicao> historico = metricaService.getHistoricoMedicoes(2);

			assertTrue(historico.size() > 0);
		} catch (NoResultFound e) {

			fail(e.getMessage());
		}
	}

	@Test
	public void testMetricaGetUltimaMedicao() {

		int idMetrica = 2;

		MedicaoClientModel medicaoViewModel = new MedicaoClientModel();
		medicaoViewModel.mac = 98745;
		medicaoViewModel.quando = new Date();
		medicaoViewModel.valor = 10;

		try {

			int idMedicao = medicaoService.criar(idMetrica, medicaoViewModel, metricaService);

			Medicao medicao = metricaService.getUltimaMedicao(idMetrica);

			assertEquals(idMedicao, medicao.getId());
		} catch (NoResultFound e) {

			fail(e.getMessage());
		}
	}
}
