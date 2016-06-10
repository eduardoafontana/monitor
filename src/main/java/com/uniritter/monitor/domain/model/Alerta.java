package com.uniritter.monitor.domain.model;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.repository.model.AlertaEventData;
import com.uniritter.monitor.domain.service.AlertaService;

@Component
public class Alerta {

	private int id;
	private AlertaRegra regra;
	private String mensagem;
	private Date created;

	private AlertaService alertaService;

	@Autowired
	public Alerta(AlertaService alertaService) {
		this.alertaService = alertaService;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public AlertaRegra getRegra() {
		return regra;
	}

	public void setRegra(AlertaRegra regra) {
		this.regra = regra;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public int save(int metricaId) {
		// testar se alerta eh valido e outras regras de negocio

		ModelMapper modelMapper = new ModelMapper();
		AlertaEventData alertaData = modelMapper.map(this, AlertaEventData.class);

		this.id = alertaService.persist(alertaData, metricaId);

		return this.id;
	}
}
