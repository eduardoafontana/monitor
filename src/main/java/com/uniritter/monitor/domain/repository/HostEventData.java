package com.uniritter.monitor.domain.repository;

import java.util.Date;

import com.uniritter.monitor.domain.model.HostGrupo;

public class HostEventData implements IEntity {
	
	private int id;
	private int ip;
	private int mac;
	private HostGrupo grupo;
	private Date created;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIp() {
		return ip;
	}

	public void setIp(int ip) {
		this.ip = ip;
	}

	public int getMac() {
		return mac;
	}

	public void setMac(int mac) {
		this.mac = mac;
	}

	public HostGrupo getGrupo() {
		return grupo;
	}

	public void setGrupo(HostGrupo grupo) {
		this.grupo = grupo;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}
