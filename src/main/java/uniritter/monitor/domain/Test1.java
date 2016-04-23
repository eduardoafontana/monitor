package uniritter.monitor.domain;
import static org.junit.Assert.*;

import org.junit.Test;


public class Test1 {

	@Test
	public void testNovaMetrica() {
		Metrica metrica = new Metrica(Tipo.EspacoEmDisco, new Host());
		assertNotNull(metrica);
		assertEquals(metrica.getTipo(), Tipo.EspacoEmDisco);
	}
	
	@Test
	public void tesNovaMedicao() {
		Metrica metrica = new Metrica(Tipo.EspacoEmDisco, new Host());
		boolean retorno = metrica.novaMedicao(8);
		
		assertTrue(retorno);
		assertTrue(metrica.historicoMedicoes().size() > 0);
		assertNotNull(metrica.historicoMedicoes().get(0));
	}
	
	@Test 
	public void testUltimaMedicao(){
		Metrica metrica = new Metrica(Tipo.EspacoEmDisco, new Host());
		metrica.novaMedicao(1);
		metrica.novaMedicao(2);
		metrica.novaMedicao(3);
		
		assertEquals(metrica.getUltimaMedicao(), metrica.historicoMedicoes().get(2));
	}
	
	@Test
	public void testNotificar(){
		Alerta alerta = new Alerta();
		
		Metrica metrica = new Metrica(Tipo.EspacoEmDisco, new Host());
		metrica.addObserver(alerta);
		metrica.novaMedicao(4);
		
		assertEquals(alerta.getMensagem(), "Alerta medição valor: 4");
	}

	@Test
	public void testMedicao() {
		Medicao medicao = new Medicao(5);
		assertNotNull(medicao);
	}
	
	@Test
	public void testHost() {
		Host host = new Host();
		assertNotNull(host);
	}
	
	@Test
	public void testAlerta() {
		Alerta alerta = new Alerta();
		assertNotNull(alerta);
	}	
}
