package com.uniritter.monitor.rest;

import static org.junit.Assert.*;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.uniritter.monitor.MonitorApplication;
import com.uniritter.monitor.domain.model.MetricaTipo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MonitorApplication.class)
@WebIntegrationTest
public class TestMedicaoController {

	@Test
	public void testCreateMedicao() {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:9000/metricas/2/medicoes");

		JSONObject request = new JSONObject();
		request.put("mac", 152364);
		request.put("valor", 25);
		request.put("quando", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date()));
		
        HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);

        ResponseEntity<String> response = restTemplate.postForEntity(builder.toUriString(), entity, String.class);
		
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	@Test
	public void testCreateMedicaoDeMetricaInexistente() {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:9000/metricas/999999/medicoes");

		JSONObject request = new JSONObject();
		request.put("mac", 152364);
		request.put("valor", 25);
		request.put("quando", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date()));
		
        HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);

        ResponseEntity<String> response = restTemplate.postForEntity(builder.toUriString(), entity, String.class);
		
		assertNotNull(response);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
	
	@Test
	public void testGetAllMedicoesDaMetrica() {

		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:9000/metricas/2/medicoes");

		HttpEntity<?> entity = new HttpEntity<>(headers);

		ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
		
		assertNotNull(response);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void testGetMedicoesDeMetricaInexistente() {

		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:9000/metricas/99999999/medicoes");

		HttpEntity<?> entity = new HttpEntity<>(headers);

		ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
		
		assertNotNull(response);
		assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
	}
}
