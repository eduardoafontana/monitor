package com.uniritter.monitor.domain.model;

import java.util.Date;

public class MetricaDados {
	public int getIP() {
		return IP;
	}
	public void setIP(int iP) {
		IP = iP;
	}
	public int getMAC() {
		return MAC;
	}
	public void setMAC(int mAC) {
		MAC = mAC;
	}
	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		Tipo = tipo;
	}
	public String getGrupo() {
		return Grupo;
	}
	public void setGrupo(String grupo) {
		Grupo = grupo;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public int IP;
	public int MAC;
	public String Tipo;
	public String Grupo;
	
	public long id;
	public String nome;
	public Date created;
}
