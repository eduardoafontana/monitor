package com.uniritter.monitor.domain.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.common.InvalidType;
import com.uniritter.monitor.domain.client.model.AlertaClientModel;
import com.uniritter.monitor.domain.model.*;
import com.uniritter.monitor.domain.repository.IAlertaRepository;
import com.uniritter.monitor.domain.repository.model.AlertaEventData;

@Component
public class AlertaService {

	private final IAlertaRepository alertaRepository;
	private final NotificacaoService notificacaoService;

	@Autowired
	public AlertaService(IAlertaRepository alertaRepository, NotificacaoService notificacaoService) {
		this.alertaRepository = alertaRepository;
		this.notificacaoService = notificacaoService;
	}

	public String[] getRegras() {
		return Arrays.toString(AlertaRegra.values()).split(", ");
	}

	public List<Alerta> getTodos(int metricaId) {
		List<AlertaEventData> alertaEventData = alertaRepository.<AlertaEventData>getListFromRelation(metricaId);

		List<Alerta> alertas = new ArrayList<Alerta>();

		for (AlertaEventData alertaData : alertaEventData) {

			Alerta alerta = new Alerta(this, notificacaoService);
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.map(alertaData, alerta);

			alertas.add(alerta);
		}

		return alertas;
	}

	public int criar(int metricaId, AlertaClientModel alertaViewModel) {

		Alerta alerta = new Alerta(this, notificacaoService);
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(alertaViewModel, alerta);

		return alerta.save(metricaId);
	}

	public int gravar(AlertaEventData alertaEventData, int metricaId) {
		return alertaRepository.saveToRelation(alertaEventData, metricaId);
	}

	public int apagarPorMetrica(int metricaId) {
		return alertaRepository.deleteFromRelation(metricaId);
	}

	public AlertaRegra verificarTipo(String regra) throws InvalidType {
		
		try {
			
			return AlertaRegra.valueOf(regra);
		} catch (IllegalArgumentException e) {
			
			throw new InvalidType("Valor regra: " + regra + " invalido!");
		}
	}
}
