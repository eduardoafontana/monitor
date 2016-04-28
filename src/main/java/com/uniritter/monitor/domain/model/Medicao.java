package com.uniritter.monitor.domain.model;

import java.util.concurrent.ThreadLocalRandom;
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
	
	public Medicao(int valor){
		this.guid = ThreadLocalRandom.current().nextInt(1, 999999);
		this.valor = valor;
		this.quando = new Date();
	}
}
