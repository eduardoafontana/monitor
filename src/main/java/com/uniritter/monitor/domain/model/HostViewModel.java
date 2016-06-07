package com.uniritter.monitor.domain.model;

import com.uniritter.monitor.domain.repository.IEntity;

public class HostViewModel implements IEntity {

	public int ip;
	public int mac;
	public String grupo;

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

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
}
