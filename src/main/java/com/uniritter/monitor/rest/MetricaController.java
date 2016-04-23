package com.uniritter.monitor.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uniritter.monitor.domain.Metrica;
import uniritter.monitor.domain.MetricaService;

@Component
@Produces("application/json")
@Consumes("application/json")
@Path("metricas")
public class MetricaController {
	
	@Autowired
	MetricaService service;
	
	@GET
	public Response getMetricas(){
		//String metrica = "{\"metrica\":\"Espaco em disco\"}"; 
		return Response.ok(service.getMetricas()).build();
	}
	
	@POST
	public Response createMetrica(Metrica metrica){//Metrica deve virar interface
		
		Metrica nova = service.createMetrica("novo nome");
		
		return Response.ok(nova).build();
	}
}
