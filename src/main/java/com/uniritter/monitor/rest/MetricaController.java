package com.uniritter.monitor.rest;

import java.util.List;

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
import com.uniritter.monitor.domain.service.NoResultFound;
import com.uniritter.monitor.domain.client.model.AlertaClientModel;
import com.uniritter.monitor.domain.client.model.HostClientModel;
import com.uniritter.monitor.domain.client.model.MedicaoClientModel;
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
		return Response.ok(metricaService.getTodos()).build();
	}

	@GET
	@Path("{id}")
	public Response getMetrica(@PathParam("id") int id) {

		try {
			return Response.status(Status.OK).entity(metricaService.getUnico(id)).build();
		} catch (NoResultFound e) {
			return Response.status(Status.NO_CONTENT).entity(e.getMessage()).build();
		}
	}

	@DELETE
	@Path("{id}")
	public Response deleteMetrica(@PathParam("id") int id) {

		int rowsDeleted = metricaService.apagar(id);

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

		int id = metricaService.criar(metricaTipoConvertido);

		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(id));

		return Response.created(builder.build()).build();
	}

	@POST
	@Path("{id}/hosts")
	public Response addHost(@PathParam("id") int id, HostClientModel hostData) {

		try {
			Metrica metrica = metricaService.getUnico(id);

			try {
				HostGrupo.valueOf(hostData.grupo);
			} catch (IllegalArgumentException e) {
				return Response.status(Status.BAD_REQUEST).entity("Valor grupo: " + hostData.grupo + " invalido!")
						.build();
			}

			// Verificar se o melhor lugar para validar os dados é na controller
			// ou
			// na service.

			int idHost = hostService.criar(metrica.getId(), hostData);

			UriBuilder builder = uriInfo.getAbsolutePathBuilder();
			builder.path(Integer.toString(idHost));

			return Response.created(builder.build()).build();

		} catch (NoResultFound e1) {
			return Response.status(Status.NO_CONTENT).entity(e1.getMessage()).build();
		}
	}

	@GET
	@Path("{id}/hosts")
	public Response getHosts(@PathParam("id") int id) {

		try {

			Metrica metrica = metricaService.getUnico(id);

			return Response.ok(hostService.getTodos(metrica.getId())).build();
		} catch (NoResultFound e) {
			return Response.status(Status.NO_CONTENT).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path("{id}/alertas")
	public Response addAlerta(@PathParam("id") int id, AlertaClientModel clientModel) {

		try {

			Metrica metrica = metricaService.getUnico(id);

			try {
				AlertaRegra.valueOf(clientModel.regra);
			} catch (IllegalArgumentException e) {
				return Response.status(Status.BAD_REQUEST).entity("Valor regra: " + clientModel.regra + " invalido!")
						.build();
			}

			// Verificar se o melhor lugar para validar os dados é na controller
			// ou
			// na service.

			int idAlerta = alertaService.criar(metrica.getId(), clientModel);

			UriBuilder builder = uriInfo.getAbsolutePathBuilder();
			builder.path(Integer.toString(idAlerta));

			return Response.created(builder.build()).build();
		} catch (NoResultFound e1) {

			return Response.status(Status.NO_CONTENT).entity(e1.getMessage()).build();
		}
	}

	@GET
	@Path("{id}/alertas")
	public Response getAlertas(@PathParam("id") int id) {

		try {

			Metrica metrica = metricaService.getUnico(id);

			return Response.ok(alertaService.getTodos(metrica.getId())).build();
		} catch (NoResultFound e) {

			return Response.status(Status.NO_CONTENT).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path("{id}/medicoes")
	public Response addMedicao(@PathParam("id") int id, MedicaoClientModel clientModel) {

		try {

			Metrica metrica = metricaService.getUnico(id);

			int idMedicao = medicaoService.criar(metrica.getId(), clientModel, metricaService);

			UriBuilder builder = uriInfo.getAbsolutePathBuilder();
			builder.path(Integer.toString(idMedicao));

			return Response.created(builder.build()).build();
		} catch (NoResultFound e) {

			return Response.status(Status.NO_CONTENT).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path("{id}/medicoes")
	public Response getMedicoes(@PathParam("id") int id) {

		try {
			Metrica metrica = metricaService.getUnico(id);

			return Response.ok(medicaoService.getTodos(metrica.getId())).build();
		} catch (NoResultFound e) {

			return Response.status(Status.NO_CONTENT).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path("{id}/medicoes/historico")
	public Response getHistoricoMedicoes(@PathParam("id") int id) {

		try {

			List<Medicao> historico = metricaService.getHistoricoMedicoes(id);

			return Response.ok(historico).build();
		} catch (NoResultFound e) {

			return Response.status(Status.NO_CONTENT).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path("{id}/medicoes/ultima")
	public Response getUltimaMedicao(@PathParam("id") int id) {

		try {
			Medicao medicao = metricaService.getUltimaMedicao(id);
			
			return Response.ok(medicao).build();
		} catch (NoResultFound e) {
			return Response.status(Status.NO_CONTENT).entity(e.getMessage()).build();
		}
	}
}
