package com.uniritter.monitor.rest;

import static org.junit.Assert.*;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.uniritter.monitor.MonitorApplication;
import com.uniritter.monitor.domain.model.MetricaTipo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MonitorApplication.class)
@WebIntegrationTest
public class TestMetricaController {
	
	@Test
	public void testGetTodasMetricas() {

		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:9000/metricas");

		HttpEntity<?> entity = new HttpEntity<>(headers);

		ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
		
		assertNotNull(response);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void testGetMetrica() {

		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:9000/metricas/2");

		HttpEntity<?> entity = new HttpEntity<>(headers);

		ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
		
		assertNotNull(response);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void testDeleteMetrica() {

		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:9000/metricas/");

		HttpEntity<String> entity = new HttpEntity<String>(MetricaTipo.CargaDeRede.toString(), headers);

		URI retorno = restTemplate.postForLocation(builder.toUriString(), entity);
		
		HttpHeaders headersD = new HttpHeaders();
		headersD.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		UriComponentsBuilder builderD = UriComponentsBuilder.fromHttpUrl(retorno.toString());

		ResponseEntity<String> responseD = restTemplate.exchange(builderD.toUriString(), HttpMethod.DELETE, new HttpEntity<>(headersD), String.class);
		
		assertNotNull(responseD);
		assertEquals(responseD.getStatusCode(), HttpStatus.OK);
	}
	
	@Test(expected=HttpClientErrorException.class)
	public void testDeleteMetricaInexistente() {
		
		RestTemplate restTemplate = new RestTemplate();		
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:9000/metricas/999999");

		restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE, new HttpEntity<>(headers), String.class);
	}
	
	@Test(expected=HttpClientErrorException.class)
	public void testGetMetricaInexistente() {

		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:9000/metricas/99999999");

		HttpEntity<?> entity = new HttpEntity<>(headers);

		restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
	}

	@Test
	public void testCreateMetrica() {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:9000/metricas/");

		HttpEntity<String> entity = new HttpEntity<String>(MetricaTipo.CargaDeRede.toString(), headers);

        ResponseEntity<String> response = restTemplate.postForEntity(builder.toUriString(), entity, String.class);
		
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	@Test(expected=HttpClientErrorException.class)
	public void testCreateMetricaTipoInvalido() {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:9000/metricas/");

		HttpEntity<String> entity = new HttpEntity<String>("qualquercoisa", headers);

        restTemplate.postForEntity(builder.toUriString(), entity, String.class);
	}	
}
