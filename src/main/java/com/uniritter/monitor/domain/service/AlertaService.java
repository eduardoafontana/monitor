package com.uniritter.monitor.domain.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.client.model.AlertaViewModel;
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

	public String[] retrieveRegras() {
		return Arrays.toString(AlertaRegra.values()).split(", ");
	}

	@SuppressWarnings("unchecked")
	public List<Alerta> retrieveAll(int metricaId) {
		List<AlertaEventData> alertaEventData = (List<AlertaEventData>) alertaRepository.getListFromRelation(metricaId);

		List<Alerta> alertas = new ArrayList<Alerta>();

		for (AlertaEventData alertaData : alertaEventData) {

			Alerta alerta = new Alerta(this, notificacaoService);
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.map(alertaData, alerta);

			alertas.add(alerta);
		}

		return alertas;
	}

	public int create(int metricaId, AlertaViewModel alertaViewModel) {

		Alerta alerta = new Alerta(this, notificacaoService);
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(alertaViewModel, alerta);

		return alerta.save(metricaId);
	}

	public int persist(AlertaEventData alertaEventData, int metricaId) {
		return alertaRepository.saveToRelation(alertaEventData, metricaId);
	}

	public int removePorMetrica(int metricaId) {
		return alertaRepository.deleteFromRelation(metricaId);
	}
}
