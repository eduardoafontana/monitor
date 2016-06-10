package com.uniritter.monitor.domain.repository.model;

import com.uniritter.monitor.domain.model.HostGrupo;

public class HostEventData extends GenericEventData {
	
	private int ip;
	private int mac;
	private HostGrupo grupo;

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
}
