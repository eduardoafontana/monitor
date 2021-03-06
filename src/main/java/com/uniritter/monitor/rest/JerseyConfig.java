package com.uniritter.monitor.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		register(MetricaController.class);
		register(MetricaTipoController.class);
		register(HostGrupoController.class);
		register(AlertaRegraController.class);
	}
}
