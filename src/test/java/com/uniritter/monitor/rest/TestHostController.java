package com.uniritter.monitor.rest;

import static org.junit.Assert.*;

import java.net.URI;

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
import com.uniritter.monitor.domain.model.HostGrupo;
import com.uniritter.monitor.domain.model.MetricaTipo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MonitorApplication.class)
@WebIntegrationTest
public class TestHostController {

	@Test
	public void testCreateHost() {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:9000/metricas/2/hosts");

		JSONObject request = new JSONObject();
		request.put("ip", 123465);
		request.put("mac", 54321);
		request.put("grupo", "Firewall");
		
        HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);

        ResponseEntity<String> response = restTemplate.postForEntity(builder.toUriString(), entity, String.class);
		
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	@Test
	public void testGetTodoHostsDaMetrica() {

		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:9000/metricas/2/hosts");

		HttpEntity<?> entity = new HttpEntity<>(headers);

		ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
		
		assertNotNull(response);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}	
}
