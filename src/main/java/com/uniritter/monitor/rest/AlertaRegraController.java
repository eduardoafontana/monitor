package com.uniritter.monitor.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.service.AlertaService;

@Component
@Produces("application/json")
@Consumes("application/json")
@Path("alertaregras")
public class AlertaRegraController {

	@Autowired
	AlertaService alerta;

	@GET
	public Response getAlertaRegras() {
		return Response.ok(alerta.getRegras()).build();
	}
}
