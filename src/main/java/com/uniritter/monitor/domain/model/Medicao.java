package com.uniritter.monitor.domain.model;

import java.util.Date;

public class Medicao {
	
	private int guid;
	private int valor;
	private Date quando;

	public int getGuid() {
		return this.guid;
	}

	public int getValor() {
		return this.valor;
	}

	public Date getQuando() {
		return this.quando;
	}
}
