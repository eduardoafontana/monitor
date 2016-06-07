package com.uniritter.monitor.domain.repository;

import java.util.Date;

import com.uniritter.monitor.domain.model.AlertaRegra;

public class AlertaEventData implements IEntity {

	private int id;
	private AlertaRegra regra;
	private String mensagem;
	private Date created;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AlertaRegra getRegra() {
		return regra;
	}

	public void setRegra(AlertaRegra regra) {
		this.regra = regra;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}
