package com.uniritter.monitor.domain.model;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.repository.model.AlertaEventData;
import com.uniritter.monitor.domain.service.AlertaService;
import com.uniritter.monitor.domain.service.NotificacaoService;
import com.uniritter.monitor.domain.model.AlertaRegra;

@Component
public class Alerta extends ControlData {

	private AlertaRegra regra;
	private String mensagem;
	private int valor;

	private AlertaService alertaService;
	private NotificacaoService notificacaoService;

	@Autowired
	public Alerta(AlertaService alertaService, NotificacaoService notificacaoService) {
		this.alertaService = alertaService;
		this.notificacaoService = notificacaoService;
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

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int save(int metricaId) {
		
		//TODO: local para aplicar regras de negocio antes de salvar. Ex: validacao

		ModelMapper modelMapper = new ModelMapper();
		AlertaEventData alertaData = modelMapper.map(this, AlertaEventData.class);

		this.id = alertaService.persist(alertaData, metricaId);

		return this.id;
	}

	public boolean RegraVerdadeira(int valorUltimaMedicao) {

		switch (this.getRegra()) {
		case Maior:
			return valorUltimaMedicao > this.getValor();
		case Menor:
			return valorUltimaMedicao < this.getValor();
		case Igual:
			return valorUltimaMedicao == this.getValor();
		}

		return false;
	}

	public void Notificar() {
		
		notificacaoService.create(this.getMensagem());
	}
}
