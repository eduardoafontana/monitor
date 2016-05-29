package com.uniritter.monitor.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.service.MetricaService;

@Component
@Produces("application/json")
@Consumes("application/json")
@Path("metricastipos")
public class MetricaTipoController {
	
	@Autowired
	MetricaService service;
	
	@GET
	public Response getTipo(){ 
		return Response.ok(service.getTipos()).build();
	}
}
