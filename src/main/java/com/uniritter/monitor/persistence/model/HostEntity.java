package com.uniritter.monitor.persistence.model;

public class HostEntity extends GenericEntity {

	private int metricaId;
	private int ip;
	private int mac;
	private String grupo;

	public int getMetricaId() {
		return metricaId;
	}

	public void setMetricaId(int metricaId) {
		this.metricaId = metricaId;
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

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
}
