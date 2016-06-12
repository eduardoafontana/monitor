package com.uniritter.monitor.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

import com.uniritter.monitor.domain.service.AlertaService;
import com.uniritter.monitor.domain.service.HostService;
import com.uniritter.monitor.domain.service.MedicaoService;
import com.uniritter.monitor.domain.service.MetricaService;
import com.uniritter.monitor.domain.client.model.AlertaViewModel;
import com.uniritter.monitor.domain.client.model.HostViewModel;
import com.uniritter.monitor.domain.client.model.MedicaoViewModel;
import com.uniritter.monitor.domain.model.*;

@Component
@Produces("application/json")
@Consumes("application/json")
@Path("metricas")
public class MetricaController {

	@Autowired
	MetricaService metricaService;

	@Autowired
	HostService hostService;

	@Autowired
	AlertaService alertaService;

	@Autowired
	MedicaoService medicaoService;
	
	@Context
	UriInfo uriInfo;

	@GET
	public Response getMetricas() {
		return Response.ok(metricaService.retrieveAll()).build();
	}

	@GET
	@Path("{id}")
	public Response getMetrica(@PathParam("id") int id) {

		Metrica metrica = metricaService.retrieve(id);

		if (metrica == null)
			return Response.status(Status.NO_CONTENT).entity(null).build();

		return Response.status(Status.OK).entity(metrica).build();
	}

	@DELETE
	@Path("{id}")
	public Response deleteMetrica(@PathParam("id") int id) {

		int rowsDeleted = metricaService.remove(id);

		if (rowsDeleted == 0)
			return Response.status(Status.NO_CONTENT).entity(null).build();

		return Response.status(Status.ACCEPTED).entity(null).build();
	}

	@POST
	public Response createMetrica(final String metricaTipo) {

		MetricaTipo metricaTipoConvertido;

		try {
			metricaTipoConvertido = MetricaTipo.valueOf(metricaTipo);
		} catch (IllegalArgumentException e) {
			return Response.status(Status.BAD_REQUEST).entity("Valor metricaTipo: " + metricaTipo + " invalido!")
					.build();
		}

		// Verificar se o melhor lugar para validar os dados é na controller ou
		// na service.

		int id = metricaService.create(metricaTipoConvertido);

		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(id));

		return Response.created(builder.build()).build();
	}

	@POST
	@Path("{id}/hosts")
	public Response addHost(@PathParam("id") int id, HostViewModel hostData) {

		Metrica metrica = metricaService.retrieve(id);

		if (metrica == null)
			return Response.status(Status.NO_CONTENT).entity(null).build();

		try {
			HostGrupo.valueOf(hostData.grupo);
		} catch (IllegalArgumentException e) {
			return Response.status(Status.BAD_REQUEST).entity("Valor grupo: " + hostData.grupo + " invalido!").build();
		}

		// Verificar se o melhor lugar para validar os dados é na controller ou
		// na service.

		int idHost = hostService.create(metrica.getId(), hostData);

		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(idHost));

		return Response.created(builder.build()).build();
	}

	@GET
	@Path("{id}/hosts")
	public Response getHosts(@PathParam("id") int id) {

		Metrica metrica = metricaService.retrieve(id);

		if (metrica == null)
			return Response.status(Status.NO_CONTENT).entity(null).build();

		// Verificar se o melhor lugar para validar os dados é na controller ou
		// na service.

		return Response.ok(hostService.retrieveAll(metrica.getId())).build();
	}
	
	@POST
	@Path("{id}/alertas")
	public Response addAlerta(@PathParam("id") int id, AlertaViewModel clientModel) {

		Metrica metrica = metricaService.retrieve(id);

		if (metrica == null)
			return Response.status(Status.NO_CONTENT).entity(null).build();

		try {
			AlertaRegra.valueOf(clientModel.regra);
		} catch (IllegalArgumentException e) {
			return Response.status(Status.BAD_REQUEST).entity("Valor regra: " + clientModel.regra + " invalido!").build();
		}

		// Verificar se o melhor lugar para validar os dados é na controller ou
		// na service.

		int idAlerta = alertaService.create(metrica.getId(), clientModel);

		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(idAlerta));

		return Response.created(builder.build()).build();
	}
	
	@GET
	@Path("{id}/alertas")
	public Response getAlertas(@PathParam("id") int id) {

		Metrica metrica = metricaService.retrieve(id);

		if (metrica == null)
			return Response.status(Status.NO_CONTENT).entity(null).build();

		// Verificar se o melhor lugar para validar os dados é na controller ou
		// na service.

		return Response.ok(alertaService.retrieveAll(metrica.getId())).build();
	}
	
	@POST
	@Path("{id}/medicoes")
	public Response addMedicao(@PathParam("id") int id, MedicaoViewModel clientModel) {

		Metrica metrica = metricaService.retrieve(id);

		if (metrica == null)
			return Response.status(Status.NO_CONTENT).entity(null).build();

		int idMedicao = medicaoService.create(metrica.getId(), clientModel);

		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(idMedicao));

		return Response.created(builder.build()).build();
	}
	
	@GET
	@Path("{id}/medicoes")
	public Response getMedicoes(@PathParam("id") int id) {

		Metrica metrica = metricaService.retrieve(id);

		if (metrica == null)
			return Response.status(Status.NO_CONTENT).entity(null).build();

		// Verificar se o melhor lugar para validar os dados é na controller ou
		// na service.

		return Response.ok(medicaoService.retrieveAll(metrica.getId())).build();
	}
}
