package com.uniritter.monitor.domain.model;

import java.util.Date;

import com.uniritter.monitor.domain.repository.IEntity;

public class Host implements IEntity {
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public int id;
	public int metricaId;
	public int ip;
	public int mac;
	public HostGrupo grupo;
	public Date created;
}
