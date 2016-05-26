package com.uniritter.monitor.rest;

import java.awt.List;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.service.MetricaService;
import com.uniritter.monitor.domain.model.*;

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
	public Response createMetrica(MetricaDados metrica){
		
		return Response.ok(service.createMetrica(metrica)).build();
	}
}
