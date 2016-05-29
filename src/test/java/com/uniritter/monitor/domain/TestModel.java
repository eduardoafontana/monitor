package com.uniritter.monitor.domain;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.uniritter.monitor.MonitorApplication;
import com.uniritter.monitor.domain.model.Alerta;
import com.uniritter.monitor.domain.model.HostGrupo;
import com.uniritter.monitor.domain.model.Host;
import com.uniritter.monitor.domain.model.Medicao;
import com.uniritter.monitor.domain.model.Metrica;
import com.uniritter.monitor.domain.model.MetricaTipo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MonitorApplication.class)
@WebAppConfiguration
public class TestModel {

	@Test
	public void testNovaMetrica() {
		Metrica metrica = new Metrica(MetricaTipo.EspacoEmDisco, new Host(HostGrupo.DbServer));
		assertNotNull(metrica);
		assertEquals(metrica.getTipo(), MetricaTipo.EspacoEmDisco);
		assertEquals(metrica.getHost().getGrupo(), HostGrupo.DbServer);
	}

	@Test
	public void testNovaMedicao() {
		Metrica metrica = new Metrica(MetricaTipo.EspacoEmDisco, new Host(HostGrupo.DbServer));
		boolean retorno = metrica.novaMedicao(8);
		
		assertTrue(retorno);
		assertTrue(metrica.historicoMedicoes().size() > 0);
		assertNotNull(metrica.historicoMedicoes().get(0));
	}
	
	@Test 
	public void testUltimaMedicao(){
		Metrica metrica = new Metrica(MetricaTipo.EspacoEmDisco, new Host(HostGrupo.DbServer));
		metrica.novaMedicao(1);
		metrica.novaMedicao(2);
		metrica.novaMedicao(3);
		
		assertEquals(metrica.getUltimaMedicao(), metrica.historicoMedicoes().get(2));
	}
	
	@Test
	public void testNotificar(){
		Alerta alerta = new Alerta();
		
		Metrica metrica = new Metrica(MetricaTipo.EspacoEmDisco, new Host(HostGrupo.DbServer));
		metrica.addObserver(alerta);
		metrica.novaMedicao(4);
		
		assertEquals(alerta.getMensagem(), "Alerta medi��o valor: 4");
	}
	
	@Test
	public void testCriarMedicao(){
		Medicao medicao = new Medicao(14);
		assertEquals(medicao.getValor(), 14);
		assertTrue(medicao.getValor() > 0 && medicao.getValor() < 1000000);
		assertTrue(medicao.getQuando().getTime() <= new Date().getTime());
	}
}
