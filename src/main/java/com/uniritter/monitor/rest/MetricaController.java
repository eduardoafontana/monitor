package com.uniritter.monitor.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

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
	
	@Context UriInfo uriInfo;
	
	@GET
	public Response getMetricas(){ 
		return Response.ok(service.getMetricas()).build();
	}
	
	@GET
	@Path("{id}")
	public Response getMetrica(@PathParam("id") int id) {

		Metrica metrica = service.getMetrica(id);
		
		if(metrica == null)
			return Response.status(Status.NO_CONTENT).entity(null).build();
		
		return Response.status(Status.OK).entity(metrica).build();
	}
	
	@POST
	public Response createMetrica(final String metricaTipo){
		
		MetricaTipo metricaTipoConvertido;
		
		try{
			metricaTipoConvertido = MetricaTipo.valueOf(metricaTipo);
		}
		catch(IllegalArgumentException e){
			return Response.status(Status.BAD_REQUEST).entity("Valor metricaTipo: " + metricaTipo + " invalido!").build();
		}
		
		int id = service.createMetrica(metricaTipoConvertido);
		
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(id));
        
        return Response.created(builder.build()).build();
	}
}
