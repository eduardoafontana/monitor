package com.uniritter.monitor.rest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.uniritter.monitor.MonitorApplication;
import com.uniritter.monitor.domain.model.HostGrupo;
import com.uniritter.monitor.domain.model.MetricaTipo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MonitorApplication.class)
@WebAppConfiguration
public class TestMetricaController {

	//http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html
	
	@Test
	public void testGetMetrica() {


//		RestTemplate restTemplate = new RestTemplate();
//		
//		HttpHeaders headers = new HttpHeaders();
//		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
//
//		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:9000/metricas");
//
//		HttpEntity<?> entity = new HttpEntity<>(headers);
//
//		HttpEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
//		
//		assertNotNull(response);
	}

//	@Test
//	public void testCreateMetrica() {
//
//
//		RestTemplate restTemplate = new RestTemplate();
//		
//		HttpHeaders headers = new HttpHeaders();
//		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
//
//		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:9000/metricas");
////		        .queryParam("ID", 123456)
////		        .queryParam("MAC", 111222333)
////		        .queryParam("Tipo",  MetricaTipo.CargaDeRede.toString())
////		        .queryParam("Grupo", HostGrupo.Firewall.toString());
//
//		HttpEntity<?> entity = new HttpEntity<>(headers);
//
//		HttpEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, String.class);
//		
//		assertNotNull(response);
//	}	

}
