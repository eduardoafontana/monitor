package com.uniritter.monitor.domain.model;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uniritter.monitor.domain.repository.model.AlertaEventData;
import com.uniritter.monitor.domain.service.AlertaService;
import com.uniritter.monitor.domain.model.AlertaRegra;

@Component
public class Alerta extends ControlData {

	private AlertaRegra regra;
	private String mensagem;
	private int valor;

	private AlertaService alertaService;

	@Autowired
	public Alerta(AlertaService alertaService) {
		this.alertaService = alertaService;
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
		// testar se alerta eh valido e outras regras de negocio

		ModelMapper modelMapper = new ModelMapper();
		AlertaEventData alertaData = modelMapper.map(this, AlertaEventData.class);

		this.id = alertaService.persist(alertaData, metricaId);

		return this.id;
	}

	public boolean RegraVerdadeira(Medicao ultimaMedicao) {

		switch (this.getRegra()) {
		case Maior:
			return ultimaMedicao.getValor() > this.getValor();
		case Menor:
			return ultimaMedicao.getValor() < this.getValor();
		case Igual:
			return ultimaMedicao.getValor() == this.getValor();
		}

		return false;
	}

	public void Notificar(Medicao ultimaMedicao) {
		System.out.println(this.getMensagem());
	}
}
